package ru.otus.shurupov.spring.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import ru.otus.shurupov.spring.authentication.config.security.NothingPasswordEncoder;
import ru.otus.shurupov.spring.authentication.config.security.SpaAuthenticationSuccessHandler;
import ru.otus.shurupov.spring.authentication.config.security.SpaLoginResponseGeneratingFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/api/**").authenticated()
                .and()
                .authorizeRequests().mvcMatchers("/**").permitAll()
                .and()
                /*.logout().logoutSuccessHandler(new SimpleLogoutSuccessHandler())
                .and()*/
                /*.apply(new SinglePageAppConfigurer<>())
                    .registerUrl("/api/register")
                    .loginUrl("/api/login")*/

                .addFilterBefore(new SpaLoginResponseGeneratingFilter(), DefaultLoginPageGeneratingFilter.class)
                .formLogin()
//                    .loginPage("/api/login")
                    .loginProcessingUrl("/api/login")
                    .successHandler(new SpaAuthenticationSuccessHandler())
                .and()
                .logout()
                    .logoutUrl("/api/logout")
                    //.logoutSuccessHandler()
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new NothingPasswordEncoder();
    }
}