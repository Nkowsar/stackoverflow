package stackOverFlow.security.services.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import stackOverFlow.security.entities.Answers;
import stackOverFlow.security.entities.Image;
import stackOverFlow.security.repositories.AnswerRepository;
import stackOverFlow.security.repositories.ImageRepository;
import stackOverFlow.stackOverFlowDTOs.dtos.TestingDto;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    AnswerRepository answerRepository;

    public void storeFile(MultipartFile multipartFile, Long answerId) throws IOException {

        Optional<Answers> optionalAnswers=answerRepository.findById(answerId);
        if(optionalAnswers.isPresent()){
            String fileName= StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            Image image=new Image();
            image.setName(fileName);
            image.setAnswers(optionalAnswers.get());
            image.setType(multipartFile.getContentType());
            image.setData(multipartFile.getBytes());
            imageRepository.save(image);

        }
        else {
            throw new IOException("answer not found");
        }
    }

    public TestingDto getDetails(){
        TestingDto testingDto=new TestingDto();
        testingDto.setId(1l);
        testingDto.setName("test");
        testingDto.setDescription("wefger");
        return testingDto;

    }
}
