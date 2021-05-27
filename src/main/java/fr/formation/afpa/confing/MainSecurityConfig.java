package fr.formation.afpa.confing;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MainSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/", "/static", "/js/**", "/css/**", "/images/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/getform")
//                .permitAll()
//                .and()
//            .logout()
//                .permitAll();
    }
}