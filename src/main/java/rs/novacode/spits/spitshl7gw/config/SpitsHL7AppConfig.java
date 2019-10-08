package rs.novacode.spits.spitshl7gw.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import rs.novacode.spits.spitshl7gw.config.persistence.PersistenceConfig;
import rs.novacode.spits.spitshl7gw.config.rest.RestFactoryConfig;
import rs.novacode.spits.spitshl7gw.repository.clientlistener.ClientListenerConfigurationRepository;
import rs.novacode.spits.spitshl7gw.service.clientlistener.ClientListenerConfigurationService;
import rs.novacode.spits.spitshl7gw.service.clientlistener.ClientListenerConfigurationServiceImp;


@Configuration
@Import({PersistenceConfig.class, RestFactoryConfig.class})
public class SpitsHL7AppConfig {

    private static String[] allowedMethods = { "OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH" };
    private static String[] exposedHeaders = { "X-Total", "X-Total-Pages", "X-Page", "X-Size" };

    @Bean
    public ClientListenerConfigurationService clientListenerConfigurationService(ClientListenerConfigurationRepository clientListenerConfigurationRepository) {
        return new ClientListenerConfigurationServiceImp(clientListenerConfigurationRepository);
    }


    // za front-end
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        for (String allowedMethodItem: allowedMethods) {
            config.addAllowedMethod(allowedMethodItem);
        }
        for (String exposedHeader: exposedHeaders) {
            config.addExposedHeader(exposedHeader);
        }
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
