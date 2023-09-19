package com.example.kommuner.service;

import com.example.kommuner.model.Kommune;
import com.example.kommuner.repository.KommuneRepository;
import com.example.kommuner.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceGetKommunerImpl implements ApiServiceGetKommuner {

    @Autowired
    private KommuneRepository kommuneRepository;

    private final String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";

    private final RestTemplate restTemplate;

    public ApiServiceGetKommunerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    private void saveKommuneer(List<Kommune> kommuner) {
        kommuner.forEach(reg -> kommuneRepository.save(reg));
    }

    private void saveKommuner(List<Kommune> kommuner) {
        for (Kommune kommune : kommuner) {
            kommuneRepository.save(kommune);
        }
    }

    public List<Kommune> getKommuner() {
        ResponseEntity<List<Kommune>> kommuneResponse =
                restTemplate.exchange(kommuneUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
        List<Kommune> kommuner = kommuneResponse.getBody();
        saveKommuner(kommuner);
        return kommuner;
    }

}
