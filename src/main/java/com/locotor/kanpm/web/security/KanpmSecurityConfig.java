package com.locotor.kanpm.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class KanpmSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private UnAuthenticationEntryPoint unAuthenticationEntryPoint;

    private AfterLogoutHandler afterLogoutHandler;

    @Autowired
    public KanpmSecurityConfig(UserDetailsService userDetailsService,
                               UnAuthenticationEntryPoint unAuthenticationEntryPoint,
                               AfterLogoutHandler afterLogoutHandler) {
        this.userDetailsService = userDetailsService;
        this.unAuthenticationEntryPoint = unAuthenticationEntryPoint;
        this.afterLogoutHandler = afterLogoutHandler;
    }

    @Bean
    public LoginFilter loginFilter(LoginSuccessHandler loginSuccessHandler, LoginFailureHandler loginFailureHandler)
            throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        loginFilter.setAuthenticationFailureHandler(loginFailureHandler);
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/auth/sign-in");
        return loginFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(unAuthenticationEntryPoint)
                .and().csrf().disable().logout().logoutUrl("/auth/logout").logoutSuccessHandler(afterLogoutHandler)
                .and().authorizeRequests().antMatchers("/auth/*")
                .permitAll().anyRequest().authenticated();

        http.addFilterAt(loginFilter(new LoginSuccessHandler(), new LoginFailureHandler()),
                UsernamePasswordAuthenticationFilter.class);
    }

}