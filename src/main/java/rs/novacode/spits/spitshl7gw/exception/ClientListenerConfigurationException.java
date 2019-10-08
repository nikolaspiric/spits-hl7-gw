package rs.novacode.spits.spitshl7gw.exception;

public class ClientListenerConfigurationException extends RuntimeException {

    public static final String DUPLICATE_CLIENT_NAME = "Client with name: %s already exists";
    public static final String ACCESS_CLIENT_EXCEPTION = "Client with name: %d differs from command: %d";

    public ClientListenerConfigurationException(String message) { super(message); }
}
