package dongyang.ac.kr.greennaePro.controller;

import dongyang.ac.kr.greennaePro.principal.AccountContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Log4j2
public class ChatController {
    @GetMapping("/{id}/chat")
    public String chatGET(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal AccountContext accountContext){
        log.info("@ChatController, chat GET()");
        model.addAttribute("accountContext",accountContext);
        return "/chat";
    }
}
