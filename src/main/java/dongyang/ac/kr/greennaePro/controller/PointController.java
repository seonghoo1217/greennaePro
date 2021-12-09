package dongyang.ac.kr.greennaePro.controller;

import dongyang.ac.kr.greennaePro.dto.PointDto;
import dongyang.ac.kr.greennaePro.principal.AccountContext;
import dongyang.ac.kr.greennaePro.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @GetMapping("/user/point")
    public @ResponseBody void point(Long amount, @AuthenticationPrincipal AccountContext accountContext,PointDto pointDto){
        log.info("point 들어옴={}",amount);
        pointDto.setAmount(amount);
        pointDto.setCreatedTime(LocalDateTime.now());
        pointService.savePoint(pointDto,accountContext.getId());
    }
}
