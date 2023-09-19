package com.example.kommuner.repository;

import com.example.kommuner.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KommuneRepository extends JpaRepository<Kommune,String> {
}
