package stackOverFlow.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stackOverFlow.security.services.image.ImageService;
import stackOverFlow.stackOverFlowDTOs.dtos.TestingDto;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    ImageService imageService;
    @PostMapping("/image/{answerId}")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile multipartFile, @PathVariable Long answerId){
        try{
            imageService.storeFile(multipartFile,answerId);
            return ResponseEntity.ok("image uploaded successfully");
        }
        catch (IOException io) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(io.getMessage());
//        } catch (java.io.IOException e) {
//            throw new RuntimeException(e);
//        }
        }


    }

    @GetMapping("/test")
    public TestingDto getTestingDto(){
        return imageService.getDetails();
    }
}
