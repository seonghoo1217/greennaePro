package dongyang.ac.kr.greennaePro.controller.api;


import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class LoginApiController {

    private final UserRepository userRepository;

    @GetMapping("/login/{username}/exist")
    public ResponseEntity<?> checkUsername(@PathVariable("username") String username){

        log.info("username={}", username);

        Optional<User> findMember = userRepository.findByUsername(username);

        if(!findMember.isPresent()){
            return new ResponseEntity<>(1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }
}
