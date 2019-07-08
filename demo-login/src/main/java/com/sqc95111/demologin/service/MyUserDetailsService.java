//package com.sqc95111.demologin.service;
//
//import com.sqc95111.demologin.model.UserDO;
//import Role;
//import User;
//import UserRole;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    RoleService roleService;
//    @Autowired
//    UserRoleService userRoleService;
//
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//
//
//    @Override
//    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
//        //这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
//        //这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
//
//
////        if (username.equals("admin")) {
////
////            //假设返回的用户信息如下;
////            UserDO userDOInMemory = new UserDO("admin", "123456", "ROLE_ADMIN", true, true, true, true);
////            return userDOInMemory;
////
////        }
//        User user = userService.getUserByUserAccountOrName(userAccount);
//        if(user==null){
//            log.info("user不存在");
//            return null;
//        }
//        UserRole userRole = userRoleService.getUserRole(user.getId());
//        if(user==null){
//            log.info("userRole不存在");
//            return null;
//        }
//        Role role = roleService.getRole(userRole.getRoleId());
//
//        UserDO userDO = new UserDO(userAccount, user.getPassword(), role.getRoleName(),true,true,true,true);
//        return userDO;
//    }
//}
//
