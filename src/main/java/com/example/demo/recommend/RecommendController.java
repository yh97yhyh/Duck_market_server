package com.example.demo.recommend;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    /*
    mahout을 이용해 사용자에게 추천
     */
    @GetMapping("/mahout/{userId}")
    public List<Recommend> recommendForUser(@PathVariable Long userId) throws TasteException {
        return recommendService.recommend(userId);
    }
}
