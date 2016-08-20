package de.alexkrieg.springreact;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.reactive.WebClient;

import static org.springframework.web.client.reactive.ClientWebRequestBuilders.get;
import static org.springframework.web.client.reactive.ResponseExtractors.body;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import reactor.core.publisher.Mono;


@RestController
public class MainController {
	
	final static String baseurl = "http://jsonplaceholder.typicode.com";
	
	@Autowired
    private WebClient webClient;// = new WebClient(new ReactorClientHttpConnector());

	@RequestMapping("react/{id}")
    public Mono<ZippedInfo> react(@PathVariable("id") long id, @RequestParam(value = "service", required=false, defaultValue="12") String serviceParam) {
		
//		Mono<String> rs = Mono.just("Wurst");
//		return rs;
		
		Mono<MainInfo> s1 = zeroStream(serviceParam, "1")
				.otherwiseIfEmpty(serviceStream(id));
		
		Mono<MainInfo> s2 = zeroStream(serviceParam, "2")
				.otherwiseIfEmpty(serviceStream(id+10));
		
    	Mono<ZippedInfo> rs =  Mono.zip( a ->  
    		                 new ZippedInfo( (MainInfo) a[0],( MainInfo) a[1]) ,s1,s2); 
		
    	return rs;
    }
	
	private Mono<MainInfo> serviceStream( long id) {
		return webClient.perform(get(baseurl+"/posts/{id}",id)
				.accept(MediaType.APPLICATION_JSON)).extract(body(MainInfo.class)).log();
	}
	
	private Mono<MainInfo> zeroStream( String serviceParam, String filter) {
		return Mono.justOrEmpty(serviceParam)
		.filter( s-> !s.contains(filter) ).map( s-> new MainInfo());
	}
	
    
    
}
