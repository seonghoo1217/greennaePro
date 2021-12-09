package dongyang.ac.kr.greennaePro.controller;

import dongyang.ac.kr.greennaePro.exception.ForbiddenException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {

    @GetMapping("/forbidden")
    public void forbidden(){
        throw new ForbiddenException("403 Forbidden");
    }
}
