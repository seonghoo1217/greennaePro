package dongyang.ac.kr.greennaePro.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            int statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "redirect:/error/404error";
            } else if (statusCode==HttpStatus.FORBIDDEN.value()){
                return "redirect:/error/403error";
            }else if(statusCode==HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "redirect:/error/500error";
            }else {
                return "redirect:/error/error";
            }
        }

        return "error/error";
    }

    @GetMapping("/error/403error")
    public String ForbiddeError(){

        return "/error/403error";
    }
    @GetMapping("/error/404error")
    public String StateError(){

        return "/error/404error";
    }
    @GetMapping("/error/500error")
    public String InternalError(){

        return "/error/500error";
    }
    @GetMapping("/error/error")
    public String AnyError(){

        return "/error/error";
    }

}
