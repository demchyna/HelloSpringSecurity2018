package com.softserve.academy.conficuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity()
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private WebAuthenticationFilter webAuthenticationFilter;
    private WebAuthenticationManager webAuthenticationManager;
    private WebAccessDeniedHandler webAccessDeniedHandler;
    private WebAuthenticationEntryPoint webAuthenticationEntryPoint;


    @Autowired
    public void setWebAuthenticationFilter(WebAuthenticationFilter webAuthenticationFilter) {
        this.webAuthenticationFilter = webAuthenticationFilter;
    }

    @Autowired
    public void setWebAuthenticationManager(WebAuthenticationManager webAuthenticationManager) {
        this.webAuthenticationManager = webAuthenticationManager;
    }

    @Autowired
    public void setWebAccessDeniedHandler(WebAccessDeniedHandler webAccessDeniedHandler) {
        this.webAccessDeniedHandler = webAccessDeniedHandler;
    }

    @Autowired
    public void setWebAuthenticationEntryPoint(WebAuthenticationEntryPoint webAuthenticationEntryPoint) {
        this.webAuthenticationEntryPoint = webAuthenticationEntryPoint;
    }

    @Bean
    public WebAuthenticationFilter getWebAuthenticationFilter() {
        WebAuthenticationFilter webAuthenticationFilter = new WebAuthenticationFilter();
        webAuthenticationFilter.setAuthenticationManager(webAuthenticationManager);
        return webAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/login").permitAll()
                .anyRequest().authenticated();
        http.httpBasic().disable()
                .formLogin().disable()
                .rememberMe().disable()
                .csrf().disable();
        http.addFilterBefore(webAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //http.exceptionHandling().accessDeniedHandler(webAccessDeniedHandler);
        http.exceptionHandling().authenticationEntryPoint(webAuthenticationEntryPoint);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                //.antMatchers(HttpMethod.POST, "/login").permitAll()
//                .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                .loginPage("/login-form")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/home")
//                .failureUrl("/login-form?error=true")
//                .permitAll()
//                    .and()
//                .logout()
//                .logoutUrl("/perform-logout")
//                .logoutSuccessUrl("/login-form")
//                .deleteCookies("JSESSIONID")
//                    .and()
//                .httpBasic()
//                    .and().csrf().disable();
//        http.addFilterBefore(webAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth, AuthenticationProvider authenticationProvider) throws Exception {
//        auth.authenticationProvider(authenticationProvider);
//    }


//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/spring-security-db");
//        dataSource.setUsername("root");
//        dataSource.setPassword("1111");
//        return dataSource;
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }


//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.userDetailsService(userDetailsService);
//
//        auth.inMemoryAuthentication().withUser("mike").password("{noop}1111").roles("WRITER");
//        auth.inMemoryAuthentication().withUser("nick").password("{noop}2222").roles("READER");
//    }


}
