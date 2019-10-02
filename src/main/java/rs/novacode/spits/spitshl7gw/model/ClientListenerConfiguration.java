package rs.novacode.spits.spitshl7gw.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_client_configuration")
public class ClientListenerConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "port")
    private int port;

    public ClientListenerConfiguration(String clientName, String ipAddress, int port) {
        this.clientName = clientName;
        this.ipAddress = ipAddress;
        this.port = port;
    }
}
