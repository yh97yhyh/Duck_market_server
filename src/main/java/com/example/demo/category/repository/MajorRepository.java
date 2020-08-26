package com.example.demo.category.repository;

import com.example.demo.category.model.MajorCategory;
import com.example.demo.category.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<MajorCategory, Long> {
    List<MajorCategory> findAll();
    boolean existsByMajorEquals(String major);
    MajorCategory findDistinctByMajor(String major);
}
