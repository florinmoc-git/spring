package OAuth2AuthorizationServer.demo;

import OAuth2AuthorizationServer.demo.entities.Client;
import OAuth2AuthorizationServer.demo.entities.OidcScope;
import OAuth2AuthorizationServer.demo.repositories.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

		// ---- DELETE ME ---- //

//		var clientRepository = applicationContext.getBean(ClientRepository.class);
//		var client = new Client();
//		client.setClientId("client");
//		client.setSecret("secret");
//		var scopes = new HashSet<OidcScope>();
//		client.setScopes(scopes);
//		client.setAuthMethod("client_secret_basic");
//		client.setGrantType("authorization_code");
//		Set<String> redirectUris = new HashSet<>(
//				Arrays.asList("https://springone.io/authorized", "https://www.baeldung.com/java-initialize-hashset"));
//		client.setRedirectUris(redirectUris);
//		clientRepository.save(client);

		// ---- DELETE ME ---- //
	}

}
