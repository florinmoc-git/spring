package patienttriagesystem.pts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import patienttriagesystem.pts.services.PatientClientService;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient patientServiceWebClient(){
        return WebClient
                .builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Bean
    public PatientClientService patientClientService(){
                return HttpServiceProxyFactory
                        .builder(WebClientAdapter.forClient(patientServiceWebClient()))
                        .build()
                        .createClient(PatientClientService.class);
    }
}
