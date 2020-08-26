package com.example.demo.merchandise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Merchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String user;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int price;

    @Column
    private String major;

    @Column
    private String sub;

    @Column
    private String imgUrl;
}
