package com.lcl.pname.controllerconfig;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcl.pname.entity.Role;
import com.lcl.pname.entity.User;
import com.lcl.pname.entity.UserRole;
import com.lcl.pname.service.RoleService;
import com.lcl.pname.service.UserRoleService;
import com.lcl.pname.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            System.out.println("用户名没有找到");
        }
        assert user != null;
        UserRole userRole = userRoleService.getOne(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
        Role role = roleService.getOne(new QueryWrapper<Role>().eq("id", userRole.getRoleId()));
        /*角色集合*/
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        /*存入角色*/
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                /*给数据库密码加密*/
                passwordEncoder.encode(user.getPassword()),
                grantedAuthorities
                );
    }
}
