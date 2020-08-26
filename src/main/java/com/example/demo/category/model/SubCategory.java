package com.example.demo.category.model;

import com.example.demo.category.model.MajorCategory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String sub;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="MAJOR_ID")
    private MajorCategory major;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MajorCategory getMajor() {
        return major;
    }

    public void setMajor(MajorCategory major) {
        this.major = major;
        if(!major.getSubs().contains(this)){
            major.getSubs().add(this);
        }
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
