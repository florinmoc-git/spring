package OAuth2AuthorizationServer.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "grant_types")
public class GrantType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String grantType;

    public GrantType(){}

    public GrantType(String grantType){
        this.grantType = grantType;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
