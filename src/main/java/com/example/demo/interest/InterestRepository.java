package com.example.demo.interest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findAllByUserId(long userId);
    List<Interest> findAllByMerchandiseId(long merchandiseId);
    Interest findById(long id);
    List<Interest> findAllByUserIdAndMerchandiseId(long userId,long merchandiseId);
}
