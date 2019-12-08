package com.waracle.cakemgr.cake;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CakeRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CakeRepository respository;

    @Test
    public void testGETCake() throws Exception {

        final int id = 1;

        ResponseEntity<String> entity = restTemplate.getForEntity(
                format("http://localhost:%d/cake/%d",port, id), String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        Cake cake = respository.findById(id).get();
        String json = new ObjectMapper().writeValueAsString(cake);

        String body = entity.getBody();
        assertEquals(json, body);
    }

    @Test
    public void testGETCakes() throws Exception {

        ResponseEntity<String> entity = restTemplate.getForEntity(
                format("http://localhost:%d/cakes",port), String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        Cake cake = respository.findById(1).get();
        String json = new ObjectMapper().writeValueAsString(singletonList(cake));

        String body = entity.getBody();
        assertEquals(json, body);
    }

    @Test
    public void testPOSTCreatesCake() throws Exception {

        int expectedId = 2;

        Cake cake = new Cake("test cake via rest","a test cake to be saved", "http://image_url");
        String json = new ObjectMapper().writeValueAsString(cake);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<String>(json, headers);

        URI locationHeader = restTemplate.postForLocation(format("http://localhost:%d/cakes", port), request);
        assertEquals(format("/cake/%d", expectedId), locationHeader.toString());

        Optional<Cake> newCake = respository.findById(expectedId);
        assertTrue("the cake was not persisted", newCake.isPresent());
    }
}
