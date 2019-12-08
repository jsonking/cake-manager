package com.waracle.cakemgr.cake;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CakeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
        String json = new ObjectMapper().writeValueAsString(savedCake);

        mockMvc.perform(get(format("/cake/%d", savedCake.getId())))
                .andExpect(status().isOk())
                .andExpect(result -> equals(json));
    }

    @Test
    public void testGETCakes() throws Exception {
        String json = new ObjectMapper().writeValueAsString(singletonList(savedCake));
        mockMvc.perform(get("/cakes/"))
                .andExpect(status().isOk())
                .andExpect(result -> equals(json));
    }

    @Test
    public void testPOSTCreatesCake() throws Exception {

        String name = "new test cake via rest";

        Cake cake = new Cake(name,"a test cake to be saved", "http://image_url");
        String json = new ObjectMapper().writeValueAsString(cake);

        MvcResult mvcResult = mockMvc.perform(post("/cakes").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/cake/")))
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        // Location header is of the form /cake/id
        String[] parts = location.split("/cake/");
        int id = Integer.parseInt(parts[1]);

        Optional<Cake> newCake = repository.findById(id);
        assertTrue("the cake was not persisted", newCake.isPresent());
        assertEquals(name, newCake.get().getName());
    }
}
