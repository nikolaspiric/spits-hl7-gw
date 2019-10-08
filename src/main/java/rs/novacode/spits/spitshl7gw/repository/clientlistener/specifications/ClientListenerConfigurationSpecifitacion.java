package rs.novacode.spits.spitshl7gw.repository.clientlistener.specifications;

import org.springframework.data.jpa.domain.Specification;
import rs.novacode.spits.spitshl7gw.model.ClientListenerConfiguration;

public class ClientListenerConfigurationSpecifitacion {

    public static Specification<ClientListenerConfiguration> forClientName(String clientName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("clientName"), clientName);
    }

    public static Specification<ClientListenerConfiguration> forAllClients(String clientName) {
        return (root, query, criteriaBuilder) -> {
            if(clientName == null) {
                return criteriaBuilder.equal(root.get("clientName"), clientName);
            } else {
                return null;
            }
        };
    }
}
