package com.waracle.cakemgr;

import com.waracle.cakemgr.cake.CakeRepository;
import com.waracle.cakemgr.cake.Cake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class CakeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CakeApplication.class, args);
    }

    @Bean
    public CommandLineRunner defaultCakes(CakeRepository repository) {
        log.info("Populating default cakes");
        return args -> repository.save(new Cake(
                "Battenberg",
                "A simple sponge cake: usually yellow and pink coloured. Decorated with marzipan and jam.",
                "https://pixfeeds.com/images/36/611695/1280-827655004-battenberg-cake.jpg"));
    }
}

