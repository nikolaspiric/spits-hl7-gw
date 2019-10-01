package rs.novacode.spits.spitshl7gw.command.clientlistener;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class ClientListenerConfigurationCommand {

    private String clientName;
    private String ipAddress;
    private int port;

    public ClientListenerConfigurationCommand(String clientName, String ipAddress, int port) {
        this.clientName = clientName;
        this.ipAddress = ipAddress;
        this.port = port;
    }
}
