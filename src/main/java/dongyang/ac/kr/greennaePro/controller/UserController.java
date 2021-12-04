package dongyang.ac.kr.greennaePro.controller;


import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.dto.UserDto;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/custom")
    public String userCustom(Model model, UserDto userDto){

        List<User> userList = userRepository.findAll();

        model.addAttribute("usersList", userList);

        return "user/custom";
    }
}
