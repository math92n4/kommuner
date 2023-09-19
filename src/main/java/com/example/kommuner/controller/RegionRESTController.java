package com.example.kommuner.controller;

import com.example.kommuner.model.Region;
import com.example.kommuner.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class RegionRESTController {

    @Autowired
    private ApiServiceGetRegioner apiServiceGetRegioner;

    @GetMapping("/regioner")
    public ResponseEntity<List<Region>> getRegioner() {
        List<Region> regioner = apiServiceGetRegioner.getRegioner();
        return new ResponseEntity<>(regioner, HttpStatus.OK);
    }
}
