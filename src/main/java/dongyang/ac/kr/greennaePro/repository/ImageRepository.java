package dongyang.ac.kr.greennaePro.repository;

import dongyang.ac.kr.greennaePro.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findByUserId(Long userId);
}
