package com.athira.fleetapp;

import com.athira.fleetapp.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//extends --> inherits & implements --> interface


@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


//    @Autowired
//    private UserDetailsService userDetailsService;
//-----------


    @Autowired
    private UserDetailsService myUserDetailsService;

    @Override// for authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
                .antMatchers("/register", "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
                .antMatchers("/users/addNew").permitAll()
//                .antMatchers("/security/user/Edit/**").hasAnyAuthority("ADMIN","SUPERADMIN")
                .antMatchers("/users").hasAnyAuthority("ADMIN", "SUPERADMIN")
                .antMatchers("/roles").hasAuthority("SUPERADMIN")
                .antMatchers("/vehicleMaintenances", "/vehicleMovements", "/vehicleHires").hasAnyAuthority("ADMIN", "SUPERADMIN", "EMPLOYEE")
                .antMatchers("/employees", "/clients", "/suppliers").hasAnyAuthority("ADMIN", "SUPERADMIN", "EMPLOYEE")
                .antMatchers("/jobTitles","employeeTypes").hasAnyAuthority("ADMIN", "SUPERADMIN", "EMPLOYEE")
                .antMatchers("/invoices","/invoiceStatuses").hasAnyAuthority("ADMIN","SUPERADMIN","EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/index")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();
    }

//    @Bean   //@ Bean is to encode the password
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

//   NoOpPasswordEncoder --> not safe

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
