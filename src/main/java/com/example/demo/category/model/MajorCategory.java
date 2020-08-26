package com.example.demo.category.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class MajorCategory {
    @Id
    @Column(name="MAJOR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String major;

    @OneToMany(mappedBy = "major")
    private List<SubCategory> subs=new ArrayList<SubCategory>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<SubCategory> getSubs() {
        return subs;
    }

    public void setSubs(List<SubCategory> subs) {
        this.subs = subs;
    }

    public void addSubs(SubCategory s){
        this.subs.add(s);
        if(s.getMajor()!=this){
            s.setMajor(this);
        }
    }
}
