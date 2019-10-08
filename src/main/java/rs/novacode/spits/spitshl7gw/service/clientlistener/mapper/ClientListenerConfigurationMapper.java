package rs.novacode.spits.spitshl7gw.service.clientlistener.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rs.novacode.spits.spitshl7gw.dto.ClientListenerConfigurationDTO;
import rs.novacode.spits.spitshl7gw.model.ClientListenerConfiguration;

@Mapper
public interface ClientListenerConfigurationMapper {

    ClientListenerConfigurationMapper CLIENT_LISTENER_CONFIGURATION_MAPPER = Mappers.getMapper(ClientListenerConfigurationMapper.class);

    ClientListenerConfigurationDTO clientConfigurationListenerToClientConfigurationListenerDTO(ClientListenerConfiguration clientListenerConfiguration);
}
