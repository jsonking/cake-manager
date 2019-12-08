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
                "Chocolate Cake",
                "A sponge cake containing lots of chocolate",
                "https://keyassets-p2.timeincuk.net/wp/prod/wp-content/uploads/sites/53/2017/11/Chocolate-sponge-cake.jpg"));
    }
}

