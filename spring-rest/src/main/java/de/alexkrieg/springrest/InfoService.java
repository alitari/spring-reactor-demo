package de.alexkrieg.springrest;

import java.util.Arrays;
import java.util.concurrent.Future;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InfoService {
	
	final static String baseurl = "http://jsonplaceholder.typicode.com";
	
	@Async
	public Future<MainInfo> serviceCall( long id) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set( "User-Agent", "MyAgent");
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		ResponseEntity<MainInfo> exchange = restTemplate.exchange(baseurl+"/posts/{id}", HttpMethod.GET, entity, MainInfo.class,id);
		return new AsyncResult<MainInfo>(exchange.getBody());
		
	}	

}
