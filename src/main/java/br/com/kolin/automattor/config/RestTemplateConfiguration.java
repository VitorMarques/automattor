package br.com.kolin.automattor.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    private static final String USER_AGENT = "User-agent";
    private static final String CURL = "curl/7.59.0";
    private static final String ACCEPT_LANGUAGE = "Accept-Language";
    private static final String PT_US_EN_LANGUAGES = "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7,es;q=0.6";
    private static final String ACCEPT_ENCODING = "Accept-Encoding";
    private static final String GZIP = "gzip";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        restTemplate.getInterceptors().add(addRequestHeader(USER_AGENT, CURL));
        restTemplate.getInterceptors().add(addRequestHeader(ACCEPT_LANGUAGE, PT_US_EN_LANGUAGES));
        //restTemplate.getInterceptors().add(addRequestHeader(ACCEPT_ENCODING, GZIP));
        return restTemplate;
    }

    private ClientHttpRequestInterceptor addRequestHeader(String name, String value) {
        return (request, body, execution) -> {
            request.getHeaders().set(name, value);
            return execution.execute(request, body);
        };
    }
}
