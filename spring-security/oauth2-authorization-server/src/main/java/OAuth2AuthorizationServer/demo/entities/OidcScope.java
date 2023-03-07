package OAuth2AuthorizationServer.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "oidc_scopes")
public class OidcScope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String oidcScope;

    public OidcScope(){}
    public OidcScope(String oidcScope){
        this.oidcScope = oidcScope;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOidcScope() {
        return oidcScope;
    }

    public void setOidcScope(String oidcScope) {
        this.oidcScope = oidcScope;
    }
}
