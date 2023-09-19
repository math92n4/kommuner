package com.example.kommuner.service;

import com.example.kommuner.model.Kommune;
import com.example.kommuner.model.Region;
import com.example.kommuner.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceGetRegionerImpl implements ApiServiceGetRegioner {

    @Autowired
    private RegionRepository regionRepository;

    private final RestTemplate restTemplate;

    private final String regionUrl = "https://api.dataforsyningen.dk/regioner";

    public ApiServiceGetRegionerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private void saveRegioner(List<Region> regioner) {
        for (Region region : regioner) {
            regionRepository.save(region);
        }
    }

    public List<Region> getRegioner() {
        ResponseEntity<List<Region>> respone =
                restTemplate.exchange(regionUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
        List<Region> regioner = respone.getBody();
        saveRegioner(regioner);
        return regioner;
    }

}
