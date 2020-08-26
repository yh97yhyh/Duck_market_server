package com.example.demo.member;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @NotBlank(message="id를 입력해주세요.")
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String nickname;

    @Pattern(regexp = "^(01(?:0|1|[6-9])-?(\\d{3}|\\d{4})-?(\\d{4})|)$")
    private String phone;

    @Column
    private String shopname;

    @Column
    @Nullable
    private String shopdes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    @Nullable
    public String getShopdes() {
        return shopdes;
    }

    public void setShopdes(@Nullable String shopdes) {
        this.shopdes = shopdes;
    }
}



