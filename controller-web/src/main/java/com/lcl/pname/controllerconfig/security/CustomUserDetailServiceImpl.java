package com.lcl.pname.controllerconfig.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcl.pname.entity.*;
import com.lcl.pname.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lcl
 * 对用户身份进行认证,搭配WebSecurityConfig类的使用
 * 从数据库读取用户信息进行身份验证,需要新建类实现 UserDetailsService 中的方法
 */
@Component //声明为组件
@Slf4j
public class CustomUserDetailServiceImpl implements UserDetailsService {

    /**
     * 指定加密方式的Bean,或者在下一步 security 配置类中注册指定
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            throw new UsernameNotFoundException("用户:" + username + "=>不存在");
        }
        log.info("user 对象" + user);
        /*UserRole userRole = userRoleService.getOne(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
        log.info("userRole 对象" + userRole);
        Role role = roleService.getOne(new QueryWrapper<Role>().eq("id", userRole.getRoleId()));
        log.info("role 对象 => " + role);*/
        /*角色集合,为了整合其他类,我把这个提成了方法*/
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        /*存入角色*/
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                /*给数据库密码加密*/
                passwordEncoder.encode(user.getPassword()),
//                grantedAuthorities
                getUserAuthority(user.getId())
        );
    }

    /**
     * 获取用户权限信息 (菜单,菜单权限)
     *
     * @param userId
     * @return 权限判定方式都会装进该集合
     */
    public List<GrantedAuthority> getUserAuthority(Long userId) {
        //返回拥有角色和权限,逗号分割,原本是 带sys开头的service,但是我的就是userService
        //String authority = userService.getUserAuthority(userId);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        /*获取角色 ID*/
        UserRole userRole = userRoleService.getOne(new QueryWrapper<UserRole>().eq("user_id", userId));
        log.info("userRole 对象 =>" + userRole);
        /*获取角色*/
        Role role = roleService.getById(userRole.getRoleId());
        log.info("role 对象 => " + role);
        /*保存角色名称*/
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        /*获取 用户-权限 映射 : 一对多*/
        List<RolePermission> rolePermission = rolePermissionService.list(new QueryWrapper<RolePermission>().eq("role_id", role.getId()));
        /*获取权限,这里暂时获取顶父级级权限*/
        rolePermission.forEach(m -> {
            Permission permission = permissionService.getById(m.getPermissionId());
            List<GrantedAuthority> grantedAuthoritiesChild = AuthorityUtils.commaSeparatedStringToAuthorityList(
                    permission.getName() + "," +
                            permission.getType() + "," +
                            permission.getPermissionValue() + "," +
                            permission.getPath()
            );
            grantedAuthorities.addAll(grantedAuthoritiesChild);
        });
        /*保存角色权限*/
        //AuthorityUtils.commaSeparatedStringToAuthorityArrayList将逗号分割的字符串转为权限列表;
        return grantedAuthorities;
    }
}
