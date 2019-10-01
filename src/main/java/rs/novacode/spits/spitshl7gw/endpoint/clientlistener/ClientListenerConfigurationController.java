package rs.novacode.spits.spitshl7gw.endpoint.clientlistener;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.novacode.spits.spitshl7gw.command.clientlistener.ClientListenerConfigurationCommand;
import rs.novacode.spits.spitshl7gw.command.clientlistener.SearchClientListenerConfigurationCommand;
import rs.novacode.spits.spitshl7gw.dto.ClientListenerConfigurationDTO;
import rs.novacode.spits.spitshl7gw.endpoint.clientlistener.util.PagingRequest;
import rs.novacode.spits.spitshl7gw.model.ClientListenerConfiguration;
import rs.novacode.spits.spitshl7gw.service.clientlistener.ClientListenerConfigurationService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;
import static rs.novacode.spits.spitshl7gw.endpoint.clientlistener.util.PagingRequest.*;

@RestController
public class ClientListenerConfigurationController {

    private static final Logger LOG = LoggerFactory.getLogger(ClientListenerConfigurationController.class);

    public static final String CONFIGURATION_CLIENTS_PATH = "/configuration/clients";
    public static final String CONFIGURATION_CLIENTS_ONE_PATH = "/configuration/client/{clientName}";
    public static final String CONFIGURATION_CLIENTS_PATH_CREATE = "/configuration/client";
    public static final String CONFIGURATION_CLIENTS_PATH_GET = "/configuration/{id}";
    public static final String CONFIGURATION_CLIENTS_PATH_DELETE = "/configuration/client/delete/{id}";
    public static final String CONFIGURATION_CLIENTS_PATH_UPDATE = "/configuration/client/update/{id}";
    private static final String CT_HEADER_KEY_TOTAL = "X-Total";

    public ClientListenerConfigurationService clientListenerConfigurationService;

    public ClientListenerConfigurationController(ClientListenerConfigurationService clientListenerConfigurationService) {
        this.clientListenerConfigurationService = clientListenerConfigurationService;
    }


    /**
     *  Fetch all Clients
     *
     * @param sort
     * @param page
     * @param size
     * @return
     */
    @GetMapping(path = CONFIGURATION_CLIENTS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientListenerConfigurationDTO>> getClients(@RequestParam(value = SORT, required = false) String sort,
                                                                           @RequestParam( value = PAGE, required = false) Integer page,
                                                                           @RequestParam(value = SIZE, required = false) Integer size){

        Page<ClientListenerConfigurationDTO> clientListenerConfigurations = clientListenerConfigurationService.findAllClients(new PagingRequest(page, size, sort).toPageable());
        HttpHeaders headers = new HttpHeaders();
        headers.add(CT_HEADER_KEY_TOTAL, String.valueOf(clientListenerConfigurations.getTotalElements()));

        return ok().headers(headers).body(clientListenerConfigurations.getContent());
    }


    /**
     *  Fetch one Client
     *
     * @param sort
     * @param page
     * @param size
     * @param command
     * @return
     */
    @PostMapping(path = CONFIGURATION_CLIENTS_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientListenerConfigurationDTO>> getClient(@RequestParam(value = SORT, required = false) String sort,
                                                                            @RequestParam( value = PAGE, required = false) Integer page,
                                                                            @RequestParam(value = SIZE, required = false) Integer size,
                                                                            @Valid @RequestBody SearchClientListenerConfigurationCommand command) {

        LOG.debug("SearchClientListenerConfigurationCommand: {}", command);

        Page<ClientListenerConfigurationDTO> clientListenerConfigurations = clientListenerConfigurationService.findAll(command, new PagingRequest(page, size, sort).toPageable());

        HttpHeaders headers = new HttpHeaders();
        headers.add(CT_HEADER_KEY_TOTAL, String.valueOf(clientListenerConfigurations.getTotalElements()));

        return ok().headers(headers).body(clientListenerConfigurations.getContent());
    }


    /**
     *  Fetch one Client by clientName
     *
     * @param clientName
     * @return
     */
    @GetMapping(path = CONFIGURATION_CLIENTS_ONE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientListenerConfiguration> getOneClientByClientName(@PathVariable("clientName") String clientName) {

        LOG.debug("Fetch configuration for client: {}", clientName);

        Optional<ClientListenerConfiguration> clientListenerConfigurations = clientListenerConfigurationService.findByClientName(clientName);

        return ok().body(clientListenerConfigurations.get());
    }


    /**
     * Create One Client
     *
     * @param clientListenerConfigurationCommand
     * @return
     */
    @PostMapping(path = CONFIGURATION_CLIENTS_PATH_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientListenerConfigurationDTO> createClientConfiguration(@Valid @RequestBody ClientListenerConfigurationCommand clientListenerConfigurationCommand){

        ClientListenerConfigurationDTO clientListenerConfigurationDTO = clientListenerConfigurationService.create(clientListenerConfigurationCommand.getClientName(), clientListenerConfigurationCommand.getIpAddress(), clientListenerConfigurationCommand.getPort());
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("id", clientListenerConfigurationDTO.getId().toString());

        return created(UriComponentsBuilder.fromUriString(CONFIGURATION_CLIENTS_PATH_GET).buildAndExpand(uriParams).toUri()).body(clientListenerConfigurationDTO);
    }


    /**
     *  Update one Client
     * @param id
     * @param command
     * @return
     */
    @PutMapping(path = CONFIGURATION_CLIENTS_PATH_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientListenerConfigurationDTO> updateClientConfiguration(@PathVariable("id") Long id,
                                                                                    @Valid @RequestBody ClientListenerConfigurationCommand command) {

        LOG.debug("Update one client by id: {}", id);

        Optional<ClientListenerConfigurationDTO> updateDTO = clientListenerConfigurationService.updateClient(id, command);
        if(updateDTO.isPresent()) {
            return ok().body(updateDTO.get());
        } else {
            return notFound().build();
        }
    }


    /**
     * Delete one Client
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = CONFIGURATION_CLIENTS_PATH_DELETE)
    public ResponseEntity deleteClientConfiguration(@PathVariable("id") Long id) {

        clientListenerConfigurationService.delete(id);

        return noContent().build();
    }
}
