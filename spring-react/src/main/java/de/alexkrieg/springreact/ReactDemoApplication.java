package de.alexkrieg.springreact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.reactive.WebClient;

@Configuration
@SpringBootApplication
public class ReactDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactDemoApplication.class, args);
	}
	
	@Bean
    public WebClient webClient(){
        WebClient webClient = new WebClient(new ReactorClientHttpConnector());
        return webClient;
    }
}
