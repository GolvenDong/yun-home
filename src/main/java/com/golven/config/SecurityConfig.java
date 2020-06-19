package com.golven.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired//注入dataSource
    private DataSource dataSource;

    @Override//重写认证方法
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String ps666 = encoder.encode("666");
        String ps123 = encoder.encode("123");
        System.out.println(ps666);
        System.out.println(ps123);
        //连接jdbc认证用户信息
        auth.jdbcAuthentication().passwordEncoder(encoder).
                dataSource(dataSource).
                //登录校验
                usersByUsernameQuery("select name ,psw ,1 from h_users where name=?")
                //登录授权
                .authoritiesByUsernameQuery("select name  ,role  from h_users where name=?");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/","/index").permitAll()
//                .antMatchers("/house/**","/users/**","/admin").hasAuthority("房东")//后台部分的管理：house、users、admin
//                .antMatchers("/index").hasAuthority("租客")
//                .anyRequest().authenticated()//对任何请求进行授权
//                .and().formLogin();//通过系统自带的登录界面登录进去后，生效以上内容
//    }
}
