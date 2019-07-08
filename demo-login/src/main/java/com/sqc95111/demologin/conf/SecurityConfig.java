//package com.sqc95111.demologin.conf;
//
//import com.sqc95111.demologin.MyAuthenticationFailHander;
//import com.sqc95111.demologin.MyAuthenticationSuccessHandler;
//import com.sqc95111.demologin.service.MyAuthenticationProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//      @Autowired
//      MyAuthenticationProvider myAuthenticationProvider;
//      @Autowired
//      MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
//      @Autowired
//      MyAuthenticationFailHander myAuthenticationFailHander;
//
//      @Override
//      protected void configure(HttpSecurity http) throws Exception {
////            // TODO Auto-generated method stub
////            //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
//////            http
//////                    .formLogin().loginPage("/login").loginProcessingUrl("/login").failureUrl("/login-error").permitAll()
//////                    .and()
//////                    .authorizeRequests()
//////                    .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagge‌​r-ui.html").permitAll()
//////                    .anyRequest().authenticated()
//////                    .and()
//////                    .csrf().disable();
//////      }
//            http
//                    .formLogin().loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
//                    .successHandler(myAuthenticationSuccessHandler)
//                    .failureHandler(myAuthenticationFailHander)
//                    .and()
//                    .authorizeRequests().antMatchers("/hello","/testRibbon").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .authorizeRequests().anyRequest().authenticated()
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/hello").permitAll()
//                    .antMatchers(HttpMethod.GET,"/whoim").hasRole("ADMIN")
////                    .anyRequest().access("@rbacService.hasPermission(request,authentication)")
//                    .and()
//                    .csrf().disable();
//      }
//
//}
//
////      @Autowired
////      public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////
////            auth.authenticationProvider(myAuthenticationProvider);
//////            auth.inMemoryAuthentication()
//////                    .withUser("admin").password("123456").roles("USER");
////      }
////}