package OAuth2AuthorizationServer.demo.entities;

import jakarta.persistence.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String clientId;
    private String secret;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "redirect_uris", joinColumns = @JoinColumn(name = "client"))
    @Column(name = "redirect_uri")
    private Set<String> redirectUris;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_oidc_scopes",
            joinColumns = @JoinColumn(name = "client"),
            inverseJoinColumns = @JoinColumn(name = "oidc_scope")
    )
    private Set<OidcScope> scopes;
    @ManyToMany
    @JoinTable(
            name = "client__client_authentication_methods",
            joinColumns = @JoinColumn(name = "client"),
            inverseJoinColumns = @JoinColumn(name = "client_authentication_method")
    )
    private Set<ClientAuthenticationMethodEnt> clientAuthenticationMethods;
//    private String grantType;
    @ManyToMany
    @JoinTable(
            name = "client_grant_types",
            joinColumns = @JoinColumn(name = "client"),
            inverseJoinColumns = @JoinColumn(name = "grant_type")
    )
    private Set<GrantType> grantTypes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public Set<OidcScope> getScopes() {
        return scopes;
    }

    public void setScopes(Set<OidcScope> scopes) {
        this.scopes = scopes;
    }

    public Set<ClientAuthenticationMethodEnt> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(Set<ClientAuthenticationMethodEnt> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public Set<GrantType> getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(Set<GrantType> grantTypes) {
        this.grantTypes = grantTypes;
    }

    public static Client from(RegisteredClient registeredClient) {
        Client client = new Client();

        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());

        client.setRedirectUris(
                registeredClient.getRedirectUris()
        );
        client.setScopes(
                registeredClient.getScopes()
                        .stream()
                        .map(OidcScope::new)
                        .collect(Collectors.toSet())
        );
        client.setClientAuthenticationMethods(
                registeredClient.getClientAuthenticationMethods()
                        .stream()
                        .map(ClientAuthenticationMethod::getValue)
                        .map(ClientAuthenticationMethodEnt::new)
                        .collect(Collectors.toSet())
        );
        client.setGrantTypes(
                registeredClient.getAuthorizationGrantTypes()
                        .stream()
                        .map(AuthorizationGrantType::getValue)
                        .map(GrantType::new)
                        .collect(Collectors.toSet())
        );

        return client;
    }

    public static RegisteredClient from(Client client) {
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .scopes(scopes -> scopes.addAll(
                        client.getScopes()
                                .stream()
                                .map(OidcScope::getOidcScope)
                                .collect(Collectors.toSet())))
                .redirectUris(redirectUris -> redirectUris.addAll(client.getRedirectUris()))
                .clientAuthenticationMethods(clientAuthenticationMethods -> clientAuthenticationMethods.addAll(
                        client.getClientAuthenticationMethods()
                                .stream()
                                .map(ClientAuthenticationMethodEnt::getClientAuthenticationMethod)
                                .map(ClientAuthenticationMethod::new)
                                .collect(Collectors.toSet())
                ))
                .authorizationGrantTypes(authorizationGrantTypes -> authorizationGrantTypes.addAll(
                        client.getGrantTypes()
                                .stream()
                                .map(GrantType::getGrantType)
                                .map(AuthorizationGrantType::new)
                                .collect(Collectors.toSet())
                ))
                .tokenSettings(TokenSettings.builder()
//            .accessTokenFormat(OAuth2TokenFormat.REFERENCE) // opaque
                        .accessTokenTimeToLive(Duration.ofHours(24)).build())
                .build();
    }
}
