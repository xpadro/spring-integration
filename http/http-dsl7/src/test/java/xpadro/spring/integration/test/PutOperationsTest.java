package xpadro.spring.integration.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import xpadro.spring.integration.test.model.ClientPerson;

@RunWith(BlockJUnit4ClassRunner.class)
public class PutOperationsTest {
	private static final String URL = "http://localhost:8080/spring/persons/{personId}";
	private final RestTemplate restTemplate = new RestTemplate();
	
	private HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON); 
		
		return headers;
	}
	
	@Test
	public void updateResource_noContentStatusCodeReturned() {
		HttpEntity<Integer> getEntity = new HttpEntity<>(buildHeaders());
		ResponseEntity<ClientPerson> response = restTemplate.exchange(URL, HttpMethod.GET, getEntity, ClientPerson.class, 4);
		ClientPerson person = response.getBody();
		person.setName("Sandra");
		HttpEntity<ClientPerson> putEntity = new HttpEntity<ClientPerson>(person, buildHeaders());
		
		response = restTemplate.exchange(URL, HttpMethod.PUT, putEntity, ClientPerson.class, 4);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
		response = restTemplate.exchange(URL, HttpMethod.GET, getEntity, ClientPerson.class, 4);
		person = response.getBody();
		assertEquals("Sandra", person.getName());
	}
	
}
