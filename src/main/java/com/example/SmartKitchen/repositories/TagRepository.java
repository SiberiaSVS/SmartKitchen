package com.example.SmartKitchen.repositories;

import com.example.SmartKitchen.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "select * from tags where name in :names", nativeQuery = true)
    List<Tag> findByNames(@Param("names") List<String> names);
    boolean existsByName(String name);
}
