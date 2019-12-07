package com.waracle.cakemgr;

import com.waracle.cakemgr.dao.CakeRepository;
import com.waracle.cakemgr.model.Cake;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CakeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CakeApplication.class, args);
    }

    @Bean
    public CommandLineRunner defaultCakes(CakeRepository repository) {
        return args -> repository.save(new Cake(
                "Battenberg",
                "A simple sponge cake: usually yellow and pink coloured. Decorated with marzipan and jam.",
                "https://pixfeeds.com/images/36/611695/1280-827655004-battenberg-cake.jpg"));
    }
}

