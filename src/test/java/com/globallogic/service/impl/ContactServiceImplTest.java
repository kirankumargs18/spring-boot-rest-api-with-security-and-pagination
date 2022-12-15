package com.globallogic.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ContactServiceImplTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void shouldReturnNotFoundForUnkonwnUserId() {

		this.webTestClient.get()
		.uri("/api/v1/contacts/{id}", 100)
		.exchange()
		.expectStatus()
				.isEqualTo(HttpStatus.NOT_FOUND);
	}
	

}
