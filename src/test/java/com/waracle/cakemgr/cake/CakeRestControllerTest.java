package com.waracle.cakemgr.cake;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CakeRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CakeRepository repository;

    private Cake savedCake;

    @Before
    public void createCake() {
        savedCake = repository.save(new Cake("rest cake", "cake created by the rest controller test", "http://image_url" ));
    }

    @After
    public void removeAllCakes() {
        repository.deleteAll();
    }

    @Test
    public void testGETCake() throws Exception {

        final int id = savedCake.getId();

        ResponseEntity<String> entity = restTemplate.getForEntity(
                format("http://localhost:%d/cake/%d",port, id), String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        String json = new ObjectMapper().writeValueAsString(savedCake);

        String body = entity.getBody();
        assertEquals(json, body);
    }

    @Test
    public void testGETCakes() throws Exception {

        ResponseEntity<String> entity = restTemplate.getForEntity(
                format("http://localhost:%d/cakes",port), String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        String json = new ObjectMapper().writeValueAsString(singletonList(savedCake));

        String body = entity.getBody();
        assertEquals(json, body);
    }

    @Test
    public void testPOSTCreatesCake() throws Exception {

        String name = "new test cake via rest";

        Cake cake = new Cake(name,"a test cake to be saved", "http://image_url");
        String json = new ObjectMapper().writeValueAsString(cake);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        URI locationHeader = restTemplate.postForLocation(format("http://localhost:%d/cakes", port), request);
        String location = locationHeader.toString();
        assertFalse("The location header was missing",location.isEmpty());

        // Location header is of the form /cake/id
        String [] parts = location.split("/cake/");
        int id = Integer.parseInt(parts[1]);

        Optional<Cake> newCake = repository.findById(id);
        assertTrue("the cake was not persisted", newCake.isPresent());
        assertEquals(name,newCake.get().getName());
    }
}
