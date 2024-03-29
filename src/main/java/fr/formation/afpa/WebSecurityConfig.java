package fr.formation.afpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import fr.formation.afpa.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	  	@Autowired
	    private UserDetailsServiceImpl userDetailsService;
	 
	    @Autowired
	    private DataSource dataSource;
	    
	 
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
	        return bCryptPasswordEncoder;
	    }
	 
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 
	        // Setting Service to find User in the database.
	        // And Setting PassswordEncoder
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }
	    
	    @Bean
	    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
	        firewall.setAllowUrlEncodedSlash(true);
	        return firewall;
	    }	
	 
	    
	    @Override
	    public void configure(WebSecurity web) {
	             //The new firewall is forced to overwrite the original
	        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	 
	    	http.csrf().disable();
	 
	        // The pages does not require login
	        http.authorizeRequests().antMatchers("/", "/connexion", "/logout").permitAll();
	 
	        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
	        // If no login, it will redirect to /login page.
	        http.authorizeRequests().antMatchers("/ajout", "/modifprofile","/reservations").access("hasAnyRole('Colocataire', 'Proprietaire')");
	        http.authorizeRequests().antMatchers("/getgestion", "/reservationsproprietaire", "/modifierreservation").access("hasAuthority('Proprietaire')");
	        http.authorizeRequests().antMatchers("/reservations", "/reservation", "/supprimerReservation").access("hasAuthority('Colocataire')");
	 
	        // For ADMIN only.
	        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
	 
	        // When the user has logged in as XX.
	        // But access a page that requires role YY,
	        // AccessDeniedException will be thrown.
	        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	 
	        // Config for Login Form
	        http.authorizeRequests().and().formLogin()//
	                // Submit URL of login page.
	                .loginProcessingUrl("/j_spring_security_check") // Submit URL
	                .loginPage("/connexion")//
	                .defaultSuccessUrl("/index")//
	                .failureUrl("/connexion?error=true")//
	                .usernameParameter("username")//
	                .passwordParameter("password")
	                // Config for Logout Page
	                .and().logout().logoutUrl("/deconnexion").logoutSuccessUrl("/index");
	 
	        // Config Remember Me.
	        http.authorizeRequests().and() //
	                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
	                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
	        
	        
	 
	    }
	 
	    @Bean
	    public PersistentTokenRepository persistentTokenRepository() {
	        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	        db.setDataSource(dataSource);
	        return db;
	    }
	
}
