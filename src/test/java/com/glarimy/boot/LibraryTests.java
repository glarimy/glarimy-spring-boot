package com.glarimy.boot;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.glarimy.boot.api.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryTests {
	@Autowired
	private TestRestTemplate rt;

	@Test
	public void testPostEntity() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		HttpEntity<Book> entity = new HttpEntity<Book>(new Book(3456, "Third"), headers);
		ResponseEntity<Book> response = rt.exchange("/library/book", HttpMethod.POST, entity, Book.class);
		System.out.println(response.getStatusCodeValue());
		System.out.println(response.getHeaders());
		assertTrue(response.getStatusCodeValue() == 200);
		assertTrue(response.getBody().getIsbn() == 3456);
	}

	@Test
	public void testPostJSON() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("{\"isbn\":4567, \"title\": \"Fourth\"}", headers);
		ResponseEntity<Book> response = rt.exchange("/library/book", HttpMethod.POST, entity, Book.class);
		System.out.println(response.getStatusCodeValue());
		System.out.println(response.getHeaders());
		assertTrue(response.getStatusCodeValue() == 200);
		assertTrue(response.getBody().getIsbn() == 4567);
	}
}