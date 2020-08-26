package com.example.demo.interest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class InterestController {

    @Autowired
    private InterestRepository interestRepository;

    /*
    사용자 PK값을 이용한 관심 검색
     */
    @GetMapping("/interest/user/{id}")
    public List<Interest> readInterestByUser(@PathVariable long id) {
        return interestRepository.findAllByUserId(id);
    }

    /*
    게시글 PK값을 이용한 관심 검색
     */
    @GetMapping("/interest/merchandise/{id}")
    public List<Interest> readInterestByMerchandise(@PathVariable long id) {
        return interestRepository.findAllByMerchandiseId(id);
    }

    /*
    사용자 PK값, 게시글 PK값을 이용한 관심 검색
     */
    @GetMapping("/interest/search")
    public List<Interest> searchInterest(@RequestBody Interest interest){
        return interestRepository.findAllByUserIdAndMerchandiseId(interest.getUserId(),interest.getMerchandiseId());
    }

    /*
    JSON을 이용한 관심 등록
     */
    @PostMapping("/interest")
    public Interest createInterest(@RequestBody Interest interest){
        return interestRepository.save(interest);
    }

    /*
    PK값으로 prefer 삭제
     */
    @GetMapping("/interest/delete/{id}")
    public void deleteInterest(@PathVariable long id){
        Interest interest=interestRepository.findById(id);
        interestRepository.delete(interest);
        log.debug("interest delete ok");
    }

    /*
    PK값으로 prefer 변경
     */
    @PostMapping("interest/update/{id}")
    public Interest updateInterest(@PathVariable long id,@RequestBody Interest interest){
        Interest origin=interestRepository.findById(id);
        origin=interest;
        log.debug("interest update ok");
        return interestRepository.save(origin);
    }
}
