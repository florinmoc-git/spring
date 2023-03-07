package OAuth2Client.demo.controllers;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoClientController {

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager; // should be injected in the proxy

    public DemoClientController(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
        this.oAuth2AuthorizedClientManager = oAuth2AuthorizedClientManager;
    }

    @GetMapping("/token")
    public String token(){
        var request = OAuth2AuthorizeRequest
                .withClientRegistrationId("1")
                .principal("client")
                .build();
        var client = oAuth2AuthorizedClientManager.authorize(request); // request to the authorization server
        return client.getAccessToken().getTokenValue(); // added on the authorization header of request "Bearer ...
    }
}
