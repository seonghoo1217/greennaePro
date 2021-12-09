package dongyang.ac.kr.greennaePro.controller;


import dongyang.ac.kr.greennaePro.domain.Point;
import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.dto.PointDto;
import dongyang.ac.kr.greennaePro.dto.UserDto;
import dongyang.ac.kr.greennaePro.principal.AccountContext;
import dongyang.ac.kr.greennaePro.repository.PointRepository;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import dongyang.ac.kr.greennaePro.service.PointService;
import dongyang.ac.kr.greennaePro.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
@Log4j2
public class UserController {

    private final UserRepository userRepository;

    private final PointRepository pointRepository;

    private final PointService pointService;

    @GetMapping("/custom")
    public String userCustom(Model model, UserDto userDto){

        List<User> userList = userRepository.findAll();

        model.addAttribute("usersList", userList);

        return "user/custom";
    }



}
