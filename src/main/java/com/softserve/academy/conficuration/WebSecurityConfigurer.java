package com.softserve.academy.conficuration;

import com.softserve.academy.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private WebAuthenticationFilter webAuthenticationFilter;
    private WebApiAuthenticationFilter webApiAuthenticationFilter;

    private WebAuthenticationEntryPoint webAuthenticationEntryPoint;
    private WebAccessDeniedHandler webAccessDeniedHandler;

    @Autowired
    public void setWebAuthenticationFilter(WebAuthenticationFilter webAuthenticationFilter) {
        this.webAuthenticationFilter = webAuthenticationFilter;
    }

    @Autowired
    public void setWebApiAuthenticationFilter(WebApiAuthenticationFilter webApiAuthenticationFilter) {
        this.webApiAuthenticationFilter = webApiAuthenticationFilter;
    }

    @Autowired
    public void setWebAccessDeniedHandler(WebAccessDeniedHandler webAccessDeniedHandler) {
        this.webAccessDeniedHandler = webAccessDeniedHandler;
    }

    @Autowired
    public void setWebAuthenticationEntryPoint(WebAuthenticationEntryPoint webAuthenticationEntryPoint) {
        this.webAuthenticationEntryPoint = webAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/login").permitAll()
                .anyRequest().authenticated();
//        http.cors().and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.httpBasic().disable()
                .formLogin().disable()
                .rememberMe().disable()
                .csrf().disable();
        http.addFilterBefore(webAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(webApiAuthenticationFilter, BasicAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(webAuthenticationEntryPoint);
        //http.exceptionHandling().accessDeniedHandler(webAccessDeniedHandler);
    }

    @Bean
    @Autowired
    public WebApiAuthenticationFilter getWebApiAuthenticationFilter(WebApiAuthenticationManager webApiAuthenticationManager) {
        WebApiAuthenticationFilter webApiAuthenticationFilter = new WebApiAuthenticationFilter("/api/**");
        webApiAuthenticationFilter.setAuthenticationManager(webApiAuthenticationManager);
        return webApiAuthenticationFilter;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/spring-security-db");
//        dataSource.setUsername("root");
//        dataSource.setPassword("1111");
//        return dataSource;
//    }

}
