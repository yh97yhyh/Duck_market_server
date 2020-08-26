package com.example.demo.category.service;

import com.example.demo.category.model.MajorCategory;
import com.example.demo.category.model.SubCategory;
import com.example.demo.category.model.input.InputCategory;
import com.example.demo.category.repository.MajorRepository;
import com.example.demo.category.repository.SubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    MajorRepository majorRepository;

    @Autowired
    SubRepository subRepository;

    public void createCategory(InputCategory inputCategory){
        //major에 이미 있는지 확인
        boolean majorExist=majorRepository.existsByMajorEquals(inputCategory.getMajor());
        System.out.println(majorExist);

        MajorCategory majorCategory=new MajorCategory();
        if(majorExist==false){
            majorCategory.setMajor(inputCategory.getMajor());
        }else{
            majorCategory=majorRepository.findDistinctByMajor(inputCategory.getMajor());
        }

        SubCategory subCategory=new SubCategory();
        subCategory.setSub(inputCategory.getSub());
        subCategory.setMajor(majorCategory);
        subRepository.save(subCategory);

        //majorCategory.addSubs(subCategory);
        majorRepository.save(majorCategory);
    }

    public List<String> readSubByMajor(String major){
        MajorCategory majorCategory=majorRepository.findDistinctByMajor(major);
        List<SubCategory> subCategories=majorCategory.getSubs();
        List<String> subs=subCategories.stream().map(subCategory -> {return subCategory.getSub();}).collect(Collectors.toList());
        return subs;
    }

    public List<String> readByMajor(){
        List<MajorCategory> majorCategories=majorRepository.findAll();
        List<String> majors=majorCategories.stream().map(majorCategory -> {return majorCategory.getMajor();}).collect(Collectors.toList());
        return majors;
    }
}
