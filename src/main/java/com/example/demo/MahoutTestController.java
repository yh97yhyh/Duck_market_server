package com.example.demo;

import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MahoutTestController {

    @Autowired
    private MahoutTestService mahoutTestService;

    @GetMapping("/mahout")
    public String test() {
        try {
            mahoutTestService.testMahout();
        } catch (TasteException | IOException e) {
            e.printStackTrace();
        }

        return "Hello World";
    }
}
