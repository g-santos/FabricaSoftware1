package br.edu.uniopet.fsw1.config;


import br.edu.uniopet.fsw1.security.CustomUserDetailsService;
import br.edu.uniopet.fsw1.security.JwtAuthenticationEntryPoint;
import br.edu.uniopet.fsw1.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Autenticação e autorização de usuario
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    //Ponto de entrada pra validar o token
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    //Criotografar senha
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //Descriptografar o token pra pegar informações
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    //Pra pegar o usuário logado
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //Configurar a url
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                //Se usuario está logado ou não
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                //O que o usuario não logado consegue fazer
                .authorizeRequests()
                    .antMatchers("/users/login", "/users/signup", "/carros", "/users")
                        .permitAll()
                    .antMatchers("/users/checkUsernameAvailability", "/users/checkEmailAvailability", "/users/isUser")
                        .permitAll()
                    .anyRequest()
                        .authenticated();

        //Adicionar o filtro antes de fazer a autenticação
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}