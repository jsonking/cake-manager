package com.waracle.cakemgr.cake;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static java.lang.String.format;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CakeWebControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testIndexPageReturnsOK() {
        ResponseEntity<String> entity = restTemplate.getForEntity(
                format("http://localhost:%d/",port), String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("cakes text was missing from homepage",entity.getBody().contains("Cakes"));
    }

    @Test
    public void testAddCakeRequestReturnsAddPage() {
        ResponseEntity<String> entity = restTemplate.getForEntity(
                format("http://localhost:%d/addCake",port), String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("add cakes text was missing from homepage",entity.getBody().contains("Add Cake"));
    }

    @Test
    public void testSaveCakeIsSuccessful_AndShownOnPage() {

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("name", "test cake1");
        parts.add("description", "a test cake to be saved");
        parts.add("imageURL", "http://image_url");

        ResponseEntity<String> entity = restTemplate.postForEntity(format("http://localhost:%d/saveCake", port), parts, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("test cake was not added",entity.getBody().contains("test cake1"));
    }
}
