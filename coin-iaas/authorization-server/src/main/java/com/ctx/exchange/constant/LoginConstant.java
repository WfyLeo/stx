package com.ctx.exchange.constant;

public class LoginConstant {
    /**
     * 后台管理人员
     */
    public static final String ADMIN_TYPE = "admin_type";
    /**
     * 普通用户
     */
    public static final String MEMBER_TYPE = "member_type";

    /**
     * 管理员的code
     */
    public static final String ADMIN_CODE = "ROLE_ADMIN";

    /**
     * 根据用户名查询管理端用户
     */
    public static final String QUERY_ADMIN_SQL = "SELECT `id` ,`username`, `password`, `status` FROM sys_user WHERE username = ? ";


    /**
     * 根据用户ID，查询其角色Code
     */
    public static final String QUERY_ROLE_CODE_SQL = "SELECT `code` FROM sys_role LEFT JOIN sys_user_role ON sys_role.id = sys_user_role.role_id WHERE sys_user_role.user_id= ?";


    /**
     * 查询全部权限
     */
    public static final String QUERY_ALL_PERMISSIONS = "select `name` from sys_privilege";

    /**
     * 根据用户ID查询权限
     */
    public static final String QUERY_PERMISSION_SQL =
            "SELECT sys_privilege.`name` FROM sys_privilege LEFT JOIN sys_role_privilege ON sys_role_privilege.privilege_id = sys_privilege.id LEFT JOIN sys_user_role  ON sys_role_privilege.role_id = sys_user_role.role_id WHERE sys_user_role.user_id = ?";



}
