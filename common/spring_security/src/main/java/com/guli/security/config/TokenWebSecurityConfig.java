package com.guli.security.config;

import com.guli.security.filter.TokenLoginFilter;
import com.guli.security.security.DefaultPasswordEncoder;
import com.guli.security.security.TokenLogoutHandler;
import com.guli.security.security.TokenManager;
import com.guli.security.security.UnauthorizedEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private TokenManager           tokenManager;
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private RedisTemplate redisTemplate;

    public TokenWebSecurityConfig(UserDetailsService userDetailsService, TokenManager tokenManager, DefaultPasswordEncoder defaultPasswordEncoder, RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.redisTemplate = redisTemplate;
    }
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().logoutUrl("/admin/acl/index/logout")//退出地址
                .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate)).and()
                .addFilter(new TokenLoginFilter(authenticationManager(),tokenManager,redisTemplate))
                .httpBasic();
    }
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }


    //不进行权限控制
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/api/**",
                "/swagger-resources/**","/v2/**","/swagger-ui.html/**");
    }

}
