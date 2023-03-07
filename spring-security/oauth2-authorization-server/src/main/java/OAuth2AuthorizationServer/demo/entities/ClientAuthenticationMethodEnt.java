package OAuth2AuthorizationServer.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "client_authentication_methods")
public class ClientAuthenticationMethodEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String clientAuthenticationMethod;

    public ClientAuthenticationMethodEnt() {
    }

    public ClientAuthenticationMethodEnt(String clientAuthenticationMethod) {
        this.clientAuthenticationMethod = clientAuthenticationMethod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientAuthenticationMethod() {
        return clientAuthenticationMethod;
    }

    public void setClientAuthenticationMethod(String clientAuthenticationMethod) {
        this.clientAuthenticationMethod = clientAuthenticationMethod;
    }
}
