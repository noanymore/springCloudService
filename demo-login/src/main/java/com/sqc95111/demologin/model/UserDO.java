//package com.sqc95111.demologin.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.io.Serializable;
//import java.util.Collection;
//
///**
// * @ClassName ${Name}
// * @Description TODO
// * @Author < a href="jcsong50@best-inc.com">sqc</a>
// * @Date 2018/12/25 16:54
// * @Version 1.0
// */
//
//public class UserDO implements Serializable,UserDetails {
//
//    private static final long serialVersionUID = 1L;
//    private String username;
//    private String password;
//    private String role;
//    private boolean accountNonExpired;
//    private boolean accountNonLocked;
//    private boolean credentialsNonExpired;
//    private boolean enabled;
//
//
//    public UserDO(String username, String password, String role, boolean accountNonExpired, boolean accountNonLocked,
//                    boolean credentialsNonExpired, boolean enabled) {
//        // TODO Auto-generated constructor stub
//        this.username = username;
//        this.password = password;
//        this.role = role;
//        this.accountNonExpired = accountNonExpired;
//        this.accountNonLocked = accountNonLocked;
//        this.credentialsNonExpired = credentialsNonExpired;
//        this.enabled = enabled;
//    }
//
//
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // TODO Auto-generated method stub
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
//    }
//    @Override
//    public String getPassword() {
//        // TODO Auto-generated method stub
//        return password;
//    }
//    @Override
//    public String getUsername() {
//        // TODO Auto-generated method stub
//        return username;
//    }
//    @Override
//    public boolean isAccountNonExpired() {
//        // TODO Auto-generated method stub
//        return accountNonExpired;
//    }
//    @Override
//    public boolean isAccountNonLocked() {
//        // TODO Auto-generated method stub
//        return accountNonLocked;
//    }
//    @Override
//    public boolean isCredentialsNonExpired() {
//        // TODO Auto-generated method stub
//        return credentialsNonExpired;
//    }
//    @Override
//    public boolean isEnabled() {
//        // TODO Auto-generated method stub
//        return enabled;
//    }
//
//}
