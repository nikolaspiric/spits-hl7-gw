package rs.novacode.spits.spitshl7gw.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientListenerConfigurationDTO {

    private Long id;
    private String clientName;
    private String ipAddress;
    private int port;
}
