package project.epic_energy_back.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableWebSecurity
public class Config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(http -> http.disable());
        httpSecurity.csrf(http -> http.disable());
        httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.cors(Customizer.withDefaults());

        //permette l'accesso a tutti dei servizi con endpoint /api/users e metodi get (naturalmente dopo l'autenticazione)
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/api/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers( "/auth/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers( "/auth/register/**").permitAll());

        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/utenti/**").permitAll());

        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/prenotazioni/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/prenotazioni/byYear/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/prenotazioni/FromCurrent/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/prenotazioni/current/**").permitAll());
        //httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/prenotazioni/soggiornoCorrente/**").permitAll());

        //nega l'accesso a qualsiasi servizio che non sia get e path /api/users
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/**").denyAll());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of(
                "http://localhost:4200/",
                "https://exact-roxy-fdinocera-9adefc80.koyeb.app/",
                "https://panoramica45.netlify.app/"
                //"https://courageous-crepe-2d7d0a.netlify.app/"
        ));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        //corsConfiguration.setMaxAge(3600L);  // Cache preflight response for 1 hour

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}