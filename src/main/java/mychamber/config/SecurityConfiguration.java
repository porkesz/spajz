package mychamber.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import mychamber.dao.UserRepository;
import mychamber.service.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(getPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
        	.antMatchers("/api/private/**").hasRole("USER")
        	.and()
        	.formLogin().loginPage("/login").permitAll();
    /*	http.csrf().disable();
    	http
	        .httpBasic()
	        .and()
	        .authorizeRequests()
	        .antMatchers("/api/private/**").hasRole("USER")
            .and()
            .formLogin().disable(); */
    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            public boolean matches(CharSequence charSequence, String s) {
            	if (charSequence.toString().equals(s)) {
            		return true;
            	}
                return false;
            }
        };
    }
}
