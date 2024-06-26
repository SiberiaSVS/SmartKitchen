package com.example.SmartKitchen.repositories;

import com.example.SmartKitchen.models.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Long> {

}
