package com.example.demo.member;

import com.example.demo.member.model.InputUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /*
    JSON을 이용한 사용자 등록
     */
    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /*
    JSON을 이용한 사용자 수정
     */
    @PostMapping("/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id) {
        User origin = userRepository.findById(id);
        origin = user;
        log.info(String.valueOf(origin.getId()));
        return userRepository.save(origin);
    }

    /*
    PK값을 통한 nickname 변경
     */
    @PostMapping("/user/nickname/{id}")
    public User updateNicknameById(@PathVariable long id, @RequestBody InputUser inputUser){
        User origin=userRepository.findById(id);
        origin.setNickname(inputUser.getChange());
        return userRepository.save(origin);
    }

    /*
    PK값을 이용한 phone 변경
     */
    @PostMapping("/user/phone/{id}")
    public User updatePhoneById(@PathVariable long id, @RequestBody InputUser inputUser){
        User origin=userRepository.findById(id);
        origin.setPhone(inputUser.getChange());
        return userRepository.save(origin);
    }

    /*
    PK값을 이용한 shopname 변경
     */
    @PostMapping("/user/shopname/{id}")
    public User updateShopnameById(@PathVariable long id, @RequestBody InputUser inputUser){
        User origin=userRepository.findById(id);
        origin.setShopname(inputUser.getChange());
        return userRepository.save(origin);
    }

    /*
    PK값을 이용한 shopdes 변경
     */
    @PostMapping("/user/shopdes/{id}")
    public User updateShopdesById(@PathVariable long id, @RequestBody InputUser inputUser){
        User origin=userRepository.findById(id);
        origin.setShopdes(inputUser.getChange());
        return userRepository.save(origin);
    }

    /*
    PK값을 통한 사용자 조회
     */
    @GetMapping("/user/{id}")
    public User readUser(@PathVariable long id) {
        return userRepository.findById(id);
    }

    /*
    Username, Password를 이용한 로그인
    - 성공시 사용자 정보 반환
    - 실패시 NULL
     */
    @PostMapping("/user/login")
    public User loginUser(@RequestBody User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
