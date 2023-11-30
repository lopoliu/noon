package com.lopo.security.config;


import com.lopo.security.component.*;
import com.lopo.security.filter.TokenLoginFilter;
import com.lopo.security.filter.UsernamePasswordLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            UsernamePasswordLoginFilter usernamePasswordLoginFilter,
            TokenLoginFilter tokenLoginFilter
    ) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/login").permitAll()  // 仅放行登陆请求、其他请求必须通过认证
                .anyRequest().authenticated();

        http.formLogin();           // 开启表单登陆

        http.csrf().disable();      // 禁用csrf保护

        http.exceptionHandling()
                        .authenticationEntryPoint(new AuthenticationExceptionHandler())
                .accessDeniedHandler(new AccessBadHandler());

        http.logout()
                .logoutRequestMatcher(
                        new OrRequestMatcher(
                                new AntPathRequestMatcher("/logout", "GET"),    // GET请求方式注销
                                new AntPathRequestMatcher("/logout", "POST")    // POST请求方式注销
                        )
                )  // 指定注销的url
                .logoutSuccessHandler(new LogoutSuccessHandler()) // 注销成功后的处理
                .invalidateHttpSession(true);    // 使会话失效

        http.addFilterBefore(tokenLoginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(usernamePasswordLoginFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public ProviderManager providerManager(PasswordEncoder passwordEncoder, UserDetailsService authenticationService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(authenticationService);
        return new ProviderManager(provider);
    }

    @Bean
    public UsernamePasswordLoginFilter usernamePasswordLoginFilter(ProviderManager providerManager){
        UsernamePasswordLoginFilter usernamePasswordLoginFilter = new UsernamePasswordLoginFilter();
        usernamePasswordLoginFilter.setFilterProcessesUrl("/login");
        usernamePasswordLoginFilter.setAuthenticationManager(providerManager);
        usernamePasswordLoginFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        usernamePasswordLoginFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
        return usernamePasswordLoginFilter;
    }

    @Bean
    public TokenLoginFilter tokenLoginFilter(){
        return new TokenLoginFilter();
    }


    /**
     * 密码加密方式为BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
