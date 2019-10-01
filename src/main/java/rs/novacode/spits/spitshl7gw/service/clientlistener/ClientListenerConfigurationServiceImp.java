package rs.novacode.spits.spitshl7gw.service.clientlistener;

import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import rs.novacode.spits.spitshl7gw.command.clientlistener.ClientListenerConfigurationCommand;
import rs.novacode.spits.spitshl7gw.command.clientlistener.SearchClientListenerConfigurationCommand;
import rs.novacode.spits.spitshl7gw.dto.ClientListenerConfigurationDTO;
import rs.novacode.spits.spitshl7gw.exception.ClientListenerConfigurationException;
import rs.novacode.spits.spitshl7gw.model.ClientListenerConfiguration;
import rs.novacode.spits.spitshl7gw.repository.clientlistener.ClientListenerConfigurationRepository;
import rs.novacode.spits.spitshl7gw.service.clientlistener.mapper.ClientListenerConfigurationMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static rs.novacode.spits.spitshl7gw.exception.ClientListenerConfigurationException.ACCESS_CLIENT_EXCEPTION;
import static rs.novacode.spits.spitshl7gw.exception.ClientListenerConfigurationException.DUPLICATE_CLIENT_NAME;
import static rs.novacode.spits.spitshl7gw.repository.clientlistener.specifications.ClientListenerConfigurationSpecifitacion.forAllClients;
import static rs.novacode.spits.spitshl7gw.repository.clientlistener.specifications.ClientListenerConfigurationSpecifitacion.forClientName;

public class ClientListenerConfigurationServiceImp implements ClientListenerConfigurationService {

    private static final Logger LOG = LoggerFactory.getLogger(ClientListenerConfigurationServiceImp.class);

    private ClientListenerConfigurationRepository clientListenerConfigurationRepository;

    public ClientListenerConfigurationServiceImp(ClientListenerConfigurationRepository clientListenerConfigurationRepository) {
        this.clientListenerConfigurationRepository = clientListenerConfigurationRepository;
    }


    @Override
    public ClientListenerConfigurationDTO create(String clientName, String ipAddress, int port) {
        Optional<ClientListenerConfiguration> clientListenerConfiguration = clientListenerConfigurationRepository.findByClientName(clientName);
        if (clientListenerConfiguration.isPresent()) {
            throw new ClientListenerConfigurationException(String.format(DUPLICATE_CLIENT_NAME, clientName));
        } else {
            ClientListenerConfiguration clientListenerConfigurationToPersist = new ClientListenerConfiguration(clientName, ipAddress, port);
            ClientListenerConfiguration persistedClientListenerConfiguration = clientListenerConfigurationRepository.save(clientListenerConfigurationToPersist);

            return ClientListenerConfigurationMapper.CLIENT_LISTENER_CONFIGURATION_MAPPER.clientConfigurationListenerToClientConfigurationListenerDTO(persistedClientListenerConfiguration);
        }
    }


    @Override
    public void delete(Long id) {
        clientListenerConfigurationRepository.deleteById(id);
    }


    @Override
    public Page<ClientListenerConfigurationDTO> findAllClients(Pageable pageable) {
        Page<ClientListenerConfiguration> clientListenerConfigurations = clientListenerConfigurationRepository.findAll(pageable);
        return new PageImpl<>(mapToClientConfigurationListenerDTO(clientListenerConfigurations.getContent()), pageable, clientListenerConfigurations.getTotalElements());
    }


    @Override
    public Page<ClientListenerConfigurationDTO> findAll(SearchClientListenerConfigurationCommand command, Pageable pageable) {
        Page<ClientListenerConfiguration> clientListenerConfigurations = clientListenerConfigurationRepository.findAll(createSpecification(command), pageable);

        return new PageImpl<>(mapToClientConfigurationListenerDTO(clientListenerConfigurations.getContent()), pageable, clientListenerConfigurations.getTotalElements());
    }


    private Specification<ClientListenerConfiguration> createSpecification(SearchClientListenerConfigurationCommand command) {
        if (StringUtils.isNotBlank(command.getClientName())) {
            return forClientName(command.getClientName());
        } else {
            return forAllClients(command.getClientName());
        }
    }


    @Override
    public Optional<ClientListenerConfiguration> findByClientName(String clientName) {
        return clientListenerConfigurationRepository.findByClientName(clientName);
    }


    @Override
    public Optional<ClientListenerConfigurationDTO> updateClient(Long clientId, ClientListenerConfigurationCommand command) {
        Optional<ClientListenerConfiguration> client = clientListenerConfigurationRepository.findById(clientId);

        if(client.isPresent()) {
            ClientListenerConfiguration oldClientRecord = client.get();
            if(clientId.equals(oldClientRecord.getId())) {
                if(client.get().getClientName().equals(command.getClientName())) {
                    oldClientRecord.setIpAddress(command.getIpAddress());
                    oldClientRecord.setPort(command.getPort());
                    return Optional.of(ClientListenerConfigurationMapper.CLIENT_LISTENER_CONFIGURATION_MAPPER.clientConfigurationListenerToClientConfigurationListenerDTO(clientListenerConfigurationRepository.save(oldClientRecord)));
                } else {
                    Optional<ClientListenerConfiguration> clientWithName = clientListenerConfigurationRepository.findByClientName(command.getClientName());
                    if(clientWithName.isPresent()) {
                        ClientListenerConfiguration clientWithNameItem = clientWithName.get();
                        if(clientWithNameItem.getId().equals(clientId)) {
                            oldClientRecord.setClientName(command.getClientName());
                            oldClientRecord.setIpAddress(command.getIpAddress());
                            oldClientRecord.setPort(command.getPort());
                            return Optional.of(ClientListenerConfigurationMapper.CLIENT_LISTENER_CONFIGURATION_MAPPER.clientConfigurationListenerToClientConfigurationListenerDTO(clientListenerConfigurationRepository.save(oldClientRecord)));
                        } else {
                            LOG.error("Trying to set client name : {} that has already been used.", command.getClientName());
                            throw new ClientListenerConfigurationException(String.format(DUPLICATE_CLIENT_NAME, command.getClientName()));
                        }
                    } else {
                        oldClientRecord.setClientName(command.getClientName());
                        oldClientRecord.setIpAddress(command.getIpAddress());
                        oldClientRecord.setPort(command.getPort());
                        return Optional.of(ClientListenerConfigurationMapper.CLIENT_LISTENER_CONFIGURATION_MAPPER.clientConfigurationListenerToClientConfigurationListenerDTO(clientListenerConfigurationRepository.save(oldClientRecord)));
                    }
                }
            } else {
                LOG.error("Trying to set data for other record [clientId: {}, commandId: {}]", clientId, client.get().getId());
                throw new ClientListenerConfigurationException(String.format(ACCESS_CLIENT_EXCEPTION, clientId, client.get().getId()));
            }
        } else {
            return Optional.empty();
        }
    }


    private List<ClientListenerConfigurationDTO> mapToClientConfigurationListenerDTO(List<ClientListenerConfiguration> clientListenerConfigurations) {
        List<ClientListenerConfigurationDTO> result = new ArrayList<>();

        if (clientListenerConfigurations != null && !clientListenerConfigurations.isEmpty()) {
            for (ClientListenerConfiguration clientListenerConfiguration : clientListenerConfigurations) {
                ClientListenerConfigurationDTO clientConfigurationListenerDTO = ClientListenerConfigurationMapper.CLIENT_LISTENER_CONFIGURATION_MAPPER.clientConfigurationListenerToClientConfigurationListenerDTO(clientListenerConfiguration);
                result.add(clientConfigurationListenerDTO);
            }
        }

        return result;
    }
}