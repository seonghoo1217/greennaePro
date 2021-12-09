package dongyang.ac.kr.greennaePro.controller.api;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.dto.PointDto;
import dongyang.ac.kr.greennaePro.principal.AccountContext;
import dongyang.ac.kr.greennaePro.repository.PointRepository;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import dongyang.ac.kr.greennaePro.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final PointRepository pointRepository;

    private final PointService pointService;

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
