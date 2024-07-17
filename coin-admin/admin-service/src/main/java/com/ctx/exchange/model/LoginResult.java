package com.ctx.exchange.model;

import com.ctx.exchange.domain.SysMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * 登录的返回值
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {

    /**
     * 登录产生的token
     */
    private String token;

    /**
     * 菜单数据
     */
    private List<SysMenu> menus;

    /**
     * 权限数据
     */
    private List<SimpleGrantedAuthority> authorities;

}
