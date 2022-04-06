package br.com.ifrndsc.CRUDCliente.config;

import br.com.ifrndsc.CRUDCliente.service.UsuarioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;
    // Autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Para codificar senha
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // Criando 2 usuários em memória e toda vez que reniciar ele é criado
        auth.inMemoryAuthentication()
                .withUser("Iramar")
                .password(passwordEncoder.encode("test"))
                .roles("USER", "ADMIN")
                .and()
                .withUser("Fulano")
                .password(passwordEncoder.encode("test"))
                .roles("USER");

        log.info("SENHA");
        log.info(passwordEncoder.encode("test"));
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

    // Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/**").hasRole("USER")
                    .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
