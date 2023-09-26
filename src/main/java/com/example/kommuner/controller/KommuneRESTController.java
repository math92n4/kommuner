package com.example.kommuner.controller;

import com.example.kommuner.model.Kommune;
import com.example.kommuner.repository.KommuneRepository;
import com.example.kommuner.service.ApiServiceGetKommuner;
import com.example.kommuner.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class KommuneRESTController {

    @Autowired
    private ApiServiceGetKommuner apiServiceGetKommuner;

    @Autowired
    private KommuneRepository kommuneRepository;

    @GetMapping("/kommuner")
    public List<Kommune> kommuner() {
        return kommuneRepository.findAll();
    }

    @GetMapping("/getkommuner")
    public List<Kommune> getKommuner() {
        return apiServiceGetKommuner.getKommuner();
    }

    @GetMapping("/kommune/{kode}")
    public ResponseEntity<Kommune> searchKommune(@PathVariable String kode) {
        Kommune kommune = kommuneRepository.findByKode(kode);
        if (kommune != null) {
            return new ResponseEntity<>(kommune, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addkommune")
    public ResponseEntity<Kommune> addKommune(@RequestBody Kommune kommune) {
        Kommune savedKommune = kommuneRepository.save(kommune);
        if (savedKommune == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(savedKommune, HttpStatus.CREATED);
        }
    }

    @PutMapping("/putkommune/{kode}")
    public ResponseEntity<Kommune> putKommune(@RequestBody Kommune kommune) {
        kommuneRepository.save(kommune);
        return new ResponseEntity<>(kommune,HttpStatus.OK);
    }

    @DeleteMapping("/deletekommune/{kode}")
    public ResponseEntity<String> deleteKommune(@PathVariable String kode) {
        Optional<Kommune> kommune = kommuneRepository.findById(kode);
        if (kommune.isPresent()) {
            kommuneRepository.deleteById(kode);
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

}
