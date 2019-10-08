package rs.novacode.spits.spitshl7gw.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.novacode.spits.spitshl7gw.endpoint.clientlistener.ClientListenerConfigurationController;
import rs.novacode.spits.spitshl7gw.service.clientlistener.ClientListenerConfigurationService;

@Configuration
public class RestFactoryConfig {

    @Bean
    public ClientListenerConfigurationController clientListenerConfigurationController(ClientListenerConfigurationService clientListenerConfigurationService) {
        return new ClientListenerConfigurationController(clientListenerConfigurationService);
    }
}
