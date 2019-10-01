package rs.novacode.spits.spitshl7gw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rs.novacode.spits.spitshl7gw.config.persistence.PersistenceConfig;
import rs.novacode.spits.spitshl7gw.config.rest.RestFactoryConfig;
import rs.novacode.spits.spitshl7gw.repository.clientlistener.ClientListenerConfigurationRepository;
import rs.novacode.spits.spitshl7gw.service.clientlistener.ClientListenerConfigurationService;
import rs.novacode.spits.spitshl7gw.service.clientlistener.ClientListenerConfigurationServiceImp;


@Configuration
@Import({PersistenceConfig.class, RestFactoryConfig.class})
public class SpitsHL7AppConfig {

    @Bean
    public ClientListenerConfigurationService clientListenerConfigurationService(ClientListenerConfigurationRepository clientListenerConfigurationRepository) {
        return new ClientListenerConfigurationServiceImp(clientListenerConfigurationRepository);
    }
}
