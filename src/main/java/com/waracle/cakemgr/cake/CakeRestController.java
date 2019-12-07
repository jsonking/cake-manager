package com.waracle.cakemgr.cake;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class CakeRestController {

    private CakeRepository cakeRepository;

    @GetMapping("/cakes")
    Iterable<Cake> getCakes() {
        return cakeRepository.findAll();
    }

    @PostMapping("/cakes")
    ResponseEntity<?> newCake(@RequestBody Cake cake) {
        Cake saved = cakeRepository.save(cake);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION,"/cake/" + saved.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/cake/{id}")
    Optional<Cake> getCake(@PathVariable Integer id) {
        return cakeRepository.findById(id);
    }
}
