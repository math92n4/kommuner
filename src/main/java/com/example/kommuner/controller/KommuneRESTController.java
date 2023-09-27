package com.example.kommuner.controller;

import com.example.kommuner.exception.ResourceNotFoundException;
import com.example.kommuner.model.Kommune;
import com.example.kommuner.repository.KommuneRepository;
import com.example.kommuner.service.ApiServiceGetKommuner;
import com.example.kommuner.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/kommunepage")
    public ResponseEntity<List<Kommune>> getKommuneInPages() {
        int page = 4;
        int size = 5;
        Pageable paging = PageRequest.of(page, size);
        Page<Kommune> pageKommuner = kommuneRepository.findAll(paging);
        List<Kommune> kommuner = pageKommuner.getContent();
        return new ResponseEntity<>(kommuner, HttpStatus.OK);
    }

    @GetMapping("/kommunepageparam")
    public ResponseEntity<Map<String, Object>> getPageOfKommuner(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page,size);
        Page<Kommune> pageKommuner = kommuneRepository.findAll(paging);
        List<Kommune> kommuner = pageKommuner.getContent();

        if (kommuner.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("kommuner", kommuner);
        response.put("currentPage", pageKommuner.getNumber());
        response.put("totalItems", pageKommuner.getTotalElements());
        response.put("totalPages", pageKommuner.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
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

    @GetMapping("/kommunenavn/{navn}")
    public ResponseEntity<Kommune> getKommuneByNavn(@PathVariable String navn) {
        var kommune = kommuneRepository.findKommuneByNavn(navn).orElseThrow(() ->
                new ResourceNotFoundException("Kommune ikke fundet med navn: " + navn));
        return new ResponseEntity<>(kommune, HttpStatus.OK);
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
