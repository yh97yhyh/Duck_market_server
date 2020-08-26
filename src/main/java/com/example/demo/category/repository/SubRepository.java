package com.example.demo.category.repository;

import com.example.demo.category.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubRepository extends JpaRepository<SubCategory,Long> {

}
