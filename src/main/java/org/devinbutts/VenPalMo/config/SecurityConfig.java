package org.devinbutts.VenPalMo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean(name="passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    Non Deprecated Version

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/**").access("hasRole('ROLE_USER')")
//                .and().formLogin().loginPage("/login").permitAll()
//                .defaultSuccessUrl("/index").failureUrl("/loginError");
//


        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login.html","/register.html").permitAll()
                .antMatchers(HttpMethod.POST, "/register**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login/submit")
                .defaultSuccessUrl("/welcome.html")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html");

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**","/js/**","/images/**");

    }





//Eric Does this
//
//    protected void configure(final HttpSecurity http) throws Exception{
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/static/**","/user/**").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/login")
//                    .and()
//                .logout()
//                    .invalidateHttpSession(true)
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/login");
//    }

}
