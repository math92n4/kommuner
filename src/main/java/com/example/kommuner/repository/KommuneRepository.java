package com.example.kommuner.repository;

import com.example.kommuner.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KommuneRepository extends JpaRepository<Kommune,String> {
    // JPQL
    Kommune findByKode(String kode);
    Optional<Kommune> findKommuneByNavn(String navn);
}
