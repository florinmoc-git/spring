package com.fm.wardservice.config;

import com.fm.wardservice.client.PatientClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    private LoadBalancedExchangeFilterFunction filterFunction;

    public WebClientConfig(LoadBalancedExchangeFilterFunction filterFunction) {
        this.filterFunction = filterFunction;
    }

    @Bean
    public WebClient patientWebClient(){
        return WebClient.builder()
                .baseUrl("http://patient-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public PatientClient patientClient(){
        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(patientWebClient()))
                .build()
                .createClient(PatientClient.class);
    }
}
