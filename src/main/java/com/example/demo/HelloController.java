package com.example.demo;

import lombok.var;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/obj")
    public TestObj obj() {
        return new TestObj(12, "Tester", "Tester Description");
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(HttpServletResponse response) throws IOException {
        var imageFile = new ClassPathResource("images/test_image.jpg");

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imageFile.getInputStream(), response.getOutputStream());

    }

    @PostMapping("/upload")
    public String uploadImage(MultipartHttpServletRequest request) throws Exception{
        MultipartFile file=request.getFile("file");
        String filePath="C:\\Users\\User\\IdeaProjects\\CSL\\src\\main\\resources\\images\\";
        String fileName = file.getOriginalFilename();
        // 파일 전송
        try {
            file.transferTo(new File(filePath + fileName));
        } catch(Exception e) {
            System.out.println("업로드 오류");
        }
        return "ok";
    }

}