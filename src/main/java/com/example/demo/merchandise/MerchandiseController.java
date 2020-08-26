package com.example.demo.merchandise;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class MerchandiseController {

    @Autowired
    private MerchandiseRepository merchandiseRepository;

    //@GetMapping("/merchandise/{id}")
    //public Merchandise readMerchandise(@PathVariable long id){
    //    return merchandiseRepository.findById(id);
    //}

    /*
    대분류를 이용한 게시글 검색
     */
    @GetMapping("/merchandise/{major}")
    public List<Merchandise> readMerchandisesByMajor(@PathVariable String major) {
        return merchandiseRepository.findAllByMajor(major);
    }

    /*
    대분류와 소분류를 이용한 게시글 검색
     */
    @GetMapping("/merchandise/{major}/{sub}")
    public List<Merchandise> readMerchandisesByMajorAndSub(@PathVariable String major, @PathVariable String sub) {
        return merchandiseRepository.findAllByMajorAndSub(major, sub);
    }

    /*
    검색어를 포함한 게시글 검색
     */
    @GetMapping("/merchandise/name/{name}")
    public List<Merchandise> readMerchandiseByName(@PathVariable String name,Merchandise merchandise){
        return merchandiseRepository.findAllByNameContaining(name);
    }

    /*
    사용자를 이용한 게시글 검색
     */
    @GetMapping("/merchandise/user/{user}")
    public List<Merchandise> readMerchandiseByUser(@PathVariable String user) {
        return merchandiseRepository.findAllByUser(user);
    }

    /*
    PK값을 이용한 게시글 검색
     */
    @GetMapping("/merchandise/id/{id}")
    public Merchandise readMerchandiseById(@PathVariable long id){
        return merchandiseRepository.findById(id);
    }
    
    /*
    JSON을 이용한 게시글 등록
     */
    @PostMapping("/merchandise")
    public Merchandise createMerchandise(@RequestBody Merchandise merchandise) {
        return merchandiseRepository.save(merchandise);
    }

    /*
    상품이미지 업로드
     */
    @PostMapping("/merchandise/image/upload")
    public String uploadImage(MultipartHttpServletRequest request) throws Exception{
        MultipartFile file=request.getFile("file");
        // String filePath="C:\\Users\\User\\IdeaProjects\\CSL\\src\\main\\resources\\images\\";
        String filePath="\\home\\ubuntu\\CSL\\src\\main\\resources\\images\\";
        String fileName = file.getOriginalFilename();
        String imgURL = filePath + fileName;
        // 파일 전송
      try {
            file.transferTo(new File(imgURL));
        } catch(Exception e) {
            System.out.println("업로드 오류");
        }
        return imgURL;
    }

    /*
    상품이미지 갖고오기
     */
    @GetMapping(value = "/merchandise/image/{imgName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable String imgName, HttpServletResponse response) throws IOException {
        String imgPath="images/"+imgName;
        var imageFile = new ClassPathResource(imgPath);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imageFile.getInputStream(), response.getOutputStream());
    }


}
