package no.salbjo16.exams.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Config class created by arcuri82
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return super.userDetailsServiceBean();
    }


    @Override
    protected void configure(HttpSecurity http) {
        try {
            http.csrf().disable();
            http.authorizeRequests()
                    .antMatchers("/", "/*", "/index.jsf", "/signup.jsf", "/stylesheet.css", "/assets/**").permitAll()
                    .antMatchers("/javax.faces.resource/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/ui/**").authenticated()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login.jsf")
                    .permitAll()
                    .failureUrl("/login.jsf?error=true")
                    .defaultSuccessUrl("/index.jsf")
                    .and()
                    .logout()
                    .logoutSuccessUrl("/index.jsf");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        try {
            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery(
                            "SELECT email, password, enabled " +
                                    "FROM users " +
                                    "WHERE email = ?"
                    )
                    .authoritiesByUsernameQuery(
                            "SELECT x.email, y.roles " +
                                    "FROM users x, user_roles y " +
                                    "WHERE x.email = ? and y.user_email = x.email "
                    )
                    /*
                        Note: in BCrypt, the "password" field also contains the salt
                     */
                    .passwordEncoder(passwordEncoder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

