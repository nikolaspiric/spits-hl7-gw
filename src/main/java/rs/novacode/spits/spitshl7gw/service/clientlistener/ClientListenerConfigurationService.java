package rs.novacode.spits.spitshl7gw.service.clientlistener;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.novacode.spits.spitshl7gw.command.clientlistener.ClientListenerConfigurationCommand;
import rs.novacode.spits.spitshl7gw.command.clientlistener.SearchClientListenerConfigurationCommand;
import rs.novacode.spits.spitshl7gw.dto.ClientListenerConfigurationDTO;
import rs.novacode.spits.spitshl7gw.model.ClientListenerConfiguration;

import java.util.Optional;

public interface ClientListenerConfigurationService {

    ClientListenerConfigurationDTO create(String clientName, String ipAddress, int port);

    void delete(Long id);

    Page<ClientListenerConfigurationDTO> findAllClients(Pageable pageable);

    Page<ClientListenerConfigurationDTO> findAll(SearchClientListenerConfigurationCommand command, Pageable pageable);

    Optional<ClientListenerConfiguration> findByClientName(String clientName);

    Optional<ClientListenerConfigurationDTO> updateClient(Long clientId, ClientListenerConfigurationCommand command);
}
