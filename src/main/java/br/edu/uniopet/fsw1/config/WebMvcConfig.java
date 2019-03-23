package br.edu.uniopet.fsw1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//Olhar o import pra ver de onde as coisas vêm.
public class WebMvcConfig implements WebMvcConfigurer {


    //Tempo máximo da execução
    private final long MAX_AGE_SECS = 3600;

    @Override
    //Permitir qualquer endereço e permitir os metodos "HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE"
    //Configuração básica é Cors
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}
