package Core.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import Core.Filter.JwtAuthenticationTokenFilter;
import Core.Handler.CustomAccessDeniedHandler;
import Core.Handler.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Register JwtAuthenticationTokenFilter
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    //Register RestAuthenticationEntryPoint
    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    //Register CustomAccessDeniedHandler
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();
        //Pages do not need login
        http.authorizeRequests().antMatchers("/", "/login", "/logout", "/register").permitAll();
        //Pages need login with any role
//        http.authorizeRequests().antMatchers("/home").access("hasAnyRole('ADMIN', 'SUPVISOR','DRIVER')");
//        //Pages only access with Admin
//        http.authorizeRequests().antMatchers("/create-supervisor", "/create-manager", "/list-supervisors").access("hasRole('ADMIN')");
        //Access Denied Exception
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        //Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginPage("/login")//
                .defaultSuccessUrl("/home")//
                .failureUrl("/login?error=true")//
                .usernameParameter("email")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
    }
}
