package com.waracle.cakemgr.cake;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CakeWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CakeRepository repository;

    @After
    public void removeAllCakes() {
        repository.deleteAll();
    }

    @Test
    public void testIndexPageReturnsOK() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Cakes")));
    }

    @Test
    public void testAddCakeRequestReturnsAddPage() throws Exception {
        mockMvc.perform(get("/addCake"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Add Cake")));
    }

    @Test
    public void testSaveCakeIsSuccessful_AndShownOnPage() throws Exception {

        MultiValueMap<String, String> parts = new LinkedMultiValueMap<>();
        parts.add("name", "test cake1");
        parts.add("description", "a test cake to be saved");
        parts.add("imageURL", "http://image_url");

        mockMvc.perform(post("/saveCake").params(parts))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test cake1")));
    }
}
