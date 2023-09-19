package com.example.kommuner.repository;

import com.example.kommuner.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,String> {
}
