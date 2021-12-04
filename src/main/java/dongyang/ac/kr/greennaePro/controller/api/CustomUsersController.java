package dongyang.ac.kr.greennaePro.controller.api;

import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
@Log4j2
public class CustomUsersController {

    private final UserRepository userRepository;

    @PostMapping("/user/{id}/upgrade")
    public ResponseEntity<?> userUpgrade(@PathVariable("id")Long id){
        User findUser = userRepository.findById(id).get();

        findUser.setRole("ROLE_MEMBER");
        userRepository.save(findUser);
        log.info("findUser={}",findUser);

        return ResponseEntity.ok().body(1);
    }

    @PostMapping("/user/{id}/delete")
    public ResponseEntity<?> userDelete(@PathVariable("id")Long id){

        log.info("id={}", id);
        User findUser = userRepository.findById(id).get();
        log.info("findUser={}",findUser);

        userRepository.delete(findUser);

        return ResponseEntity.ok().body(1);
    }
}
