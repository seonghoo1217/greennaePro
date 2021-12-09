package dongyang.ac.kr.greennaePro.controller.api;


import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.dto.PointDto;
import dongyang.ac.kr.greennaePro.principal.AccountContext;
import dongyang.ac.kr.greennaePro.repository.PointRepository;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import dongyang.ac.kr.greennaePro.service.PointService;
import dongyang.ac.kr.greennaePro.service.UserService;
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

    private final UserService userService;

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

    @GetMapping("/{id}/like")
    public ResponseEntity<?> userLike(@PathVariable("id")Long id){
        log.info("좋아요 들어옴");
        User user = userRepository.findById(id).get();
        int like = user.getLike();
        log.info("newlike={}",like);

        userService.updateUserLike(id,user.getLike());

        return new ResponseEntity<>(1,HttpStatus.OK);
    }

    @GetMapping("/{id}/getmap")
    public ResponseEntity<?> getXY(@PathVariable("id") Long id,String x,String y){
        log.info("getmap들어옴 ㅋㅋ");
        log.info("this is x={}",x);
        log.info("this is y={},",y);
        User user = userRepository.findById(id).get();
        userService.updateXY(user.getId(),x,y);

        return new ResponseEntity<>(1,HttpStatus.OK);
    }
}
