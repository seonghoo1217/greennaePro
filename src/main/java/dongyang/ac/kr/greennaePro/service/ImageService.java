package dongyang.ac.kr.greennaePro.service;

import dongyang.ac.kr.greennaePro.domain.Image;
import dongyang.ac.kr.greennaePro.repository.ImageRepository;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class ImageService {

    private final ImageRepository imageRepository;

    private final UserRepository userRepository;

    @Transactional
    public void saveImage(Image image){

        imageRepository.save(image);
    }

    public List<Image> getImageList(Long userId){
        return imageRepository.findByUserId(userId);
    }
}
