package com.reymar.lesson4.controller;

import com.reymar.lesson4.model.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	void setUp() {
		restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
		testRestTemplate = new TestRestTemplate(restTemplateBuilder);
	}
	
	@DisplayName("Obtener todos los laptos")
	@Test
	void getLaptops() {
		ResponseEntity<Laptop[]> response =
				// peticion get (a la url x, recibe un objeto tipo lista laptop)
				testRestTemplate.getForEntity("/api/laptop", Laptop[].class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(200, response.getStatusCodeValue());
		
		Optional<Laptop[]> laptops = Optional.ofNullable(response.getBody());
		assumeTrue(laptops.isPresent());
		assertTrue(Arrays.asList(laptops.get()).size() > 0);
	}
	
	@Test
	void postLaptop() {
		HttpEntity<String> request = mockJson();
		
		ResponseEntity<Long> response =
				testRestTemplate.exchange(
						"/api/laptop",
						HttpMethod.POST,
						request,
						Long.class);
		
		Long result = response.getBody();
		
		assumeTrue(result != null);
		assertTrue(result >= 1L);
	}
	
	@Test
	void putLaptop() {
		HttpEntity<String> request = mockJson();
		
		ResponseEntity<Laptop> response =
				testRestTemplate.exchange(
						"/api/laptop/2",
						HttpMethod.PUT,
						request,
						Laptop.class);
		
		assumeTrue(response.getStatusCode() == HttpStatus.OK);
		assertEquals("APPLE", response.getBody().getMarca());
	}
	
	@Test
	void deleteLaptop() {
		ResponseEntity<?> response =
				testRestTemplate.exchange(
						"/api/laptop/1",
						HttpMethod.DELETE,
						HttpEntity.EMPTY,
						Void.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	// An HTTP entity is the majority of an HTTP request or response, consisting of some of the headers and the body, if present
	private HttpEntity<String> mockJson() {
		// simular un json
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		
		String json = """
				{
					"marca": "APPLE",
					"modelo": "mac",
					"dueno": "olga"
				}
				""";
		
		return new HttpEntity<>(json, headers);
	}
}