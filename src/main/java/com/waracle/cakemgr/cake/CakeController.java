package com.waracle.cakemgr.cake;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CakeController {

    private CakeRepository cakeRepository;

    @GetMapping("/cakes")
    Iterable<Cake> getCakes() {
        return cakeRepository.findAll();
    }

    @PostMapping("/cakes")
    ResponseEntity<?> newCake(@RequestBody Cake cake) {
        cakeRepository.save(cake);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
