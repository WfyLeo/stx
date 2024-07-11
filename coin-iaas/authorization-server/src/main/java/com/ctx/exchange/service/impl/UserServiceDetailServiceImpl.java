package com.ctx.exchange.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.ctx.exchange.constant.LoginConstant.*;

@Service
public class UserServiceDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取请求上下文的对象头
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        //获取请求参数loginType
        String loginType = requestAttributes.getRequest().getParameter("login_type");
        //获取授权类型
        String grantType = requestAttributes.getRequest().getParameter("grant_type");
        //如果授权类型属于刷新令牌 就修改用户名，因为生成token的时候，把userName设置为Id了
        if(REFRESH_TOKEN.equalsIgnoreCase(grantType)) {
            userName = adjustUsername(userName, loginType);
        }
        if(StringUtils.isBlank(loginType)){
            throw new AuthenticationServiceException("缺少登录类型");
        }
        UserDetails userDetails = null;
        try{
            switch (loginType){
                //管理员登录
                case ADMIN_TYPE:
                    userDetails = loadAdminUserByUserName(userName);
                    break;
                //普通用户登录
                case MEMBER_TYPE:
                    userDetails = loadMemberUserByUserName(userName);
                    break;
                default:
                    throw new AuthenticationServiceException("暂不支持登录类型:"+loginType);
            }
        }catch (IncorrectResultSizeDataAccessException e){
            throw new UsernameNotFoundException("会员"+userName+"不存在");
        }
        return userDetails;
    }

    /**
     * 如果是刷新令牌，根据用户ID，和登录类型，查询其用户名
     * @param userName
     * @param loginType
     * @return
     */
    private String adjustUsername(String userName, String loginType) {
        String name;
        switch (loginType){
            case ADMIN_TYPE:
                name = jdbcTemplate.queryForObject(QUERY_ADMIN_USER_WITH_ID, String.class, userName);
                break;
            case MEMBER_TYPE:
                name= jdbcTemplate.queryForObject(QUERY_MEMBER_USER_WITH_ID, String.class, userName);
                break;
            default:
                throw new AuthenticationServiceException("暂不支持登录类型:"+loginType);
        }
        return name;
    }

    /**
     * 普通用户进行一个登录 根据用户名查询
     * @param userName
     * @return
     */
    private UserDetails loadMemberUserByUserName(String userName) {
        return jdbcTemplate.queryForObject(QUERY_MEMBER_SQL, (resultSet, i) -> {
            if(resultSet.wasNull()){
                throw new UsernameNotFoundException("用户："+userName+"不存在");
            }
            //获取用户的ID
            long id = resultSet.getLong("id");
            String password = resultSet.getString("password");
            byte status = resultSet.getByte("status");
            //ROLE_USER 是默认的权限
            return new User(String.valueOf(id),password,status==1,true,true,true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        },userName,userName);
    }

    /**
     * 管理员进行一个登录 根据用户名查询
     * @param userName
     * @return
     */
    private UserDetails loadAdminUserByUserName(String userName) {
        //先查询角色code
        return jdbcTemplate.queryForObject(QUERY_ADMIN_SQL, (resultSet, i) -> {
            if(resultSet.wasNull()){
                throw new UsernameNotFoundException("用户："+userName+"不存在");
            }
            //获取用户的ID
            long id = resultSet.getLong("id");
            String password = resultSet.getString("password");
            byte status = resultSet.getByte("status");
            return new User(String.valueOf(id),password,status==1,true,true,true,getUserPermissions(id));
        },userName);
    }

    private Collection<? extends GrantedAuthority> getUserPermissions(long id) {
        //查询用户的角色
        String roleCode = jdbcTemplate.queryForObject(QUERY_ROLE_CODE_SQL, String.class,id);
        if(StringUtils.isBlank(roleCode)){
            throw new UsernameNotFoundException("用户角色编码不存在");
        }
        List<String> permissionList = null;
        //超级管理员
        if(ADMIN_CODE.equals(roleCode)){
            permissionList = jdbcTemplate.queryForList(QUERY_ALL_PERMISSIONS, String.class);
        }else{
            permissionList = jdbcTemplate.queryForList(QUERY_PERMISSION_SQL, String.class, id);
        }
        if(CollectionUtils.isEmpty(permissionList)){
            return Collections.EMPTY_SET;
        }
        return permissionList.stream().distinct().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
