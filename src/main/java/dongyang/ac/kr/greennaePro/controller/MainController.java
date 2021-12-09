package dongyang.ac.kr.greennaePro.controller;

import dongyang.ac.kr.greennaePro.domain.Image;
import dongyang.ac.kr.greennaePro.domain.Room;
import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.dto.PageDto;
import dongyang.ac.kr.greennaePro.principal.AccountContext;
import dongyang.ac.kr.greennaePro.repository.ImageRepository;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import dongyang.ac.kr.greennaePro.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.Banner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//메인 컨트롤러 컨트롤러란 URL과 뷰(html파일)를 매핑 시켜주는것
@Controller
@RequestMapping("/")//RequestMapping은 공통된 URl넣을떄
@RequiredArgsConstructor
@Log4j2
public class MainController {

    List<Room> roomList = new ArrayList<Room>();
    static int roomNumber = 0;

    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal AccountContext accountContext, User user,
                        @PageableDefault(size = 16,sort = "createdTime",direction = Sort.Direction.DESC)Pageable pageable){
            if (accountContext.getImageName()==null){
                return "redirect:/imageRegi";
            }
            else {
                PageImpl<User> results = userRepository.userpaging(accountContext.getId(), pageable);
                model.addAttribute("user",results);
                model.addAttribute("page",new PageDto(results.getTotalElements(),pageable));
                model.addAttribute("accountContext", accountContext);
                return "/index";
            }
    }
    @GetMapping("/imageRegi")
    public String imageRegiSterForm(){

        return "/imageRegi";
    }
    @PostMapping("/imageRegi")
    public String imageRegiSter(){

        return "redirect:/login/signin";
    }

    @GetMapping("/myprofile")
    public String profile(Model model,Image image, @AuthenticationPrincipal AccountContext accountContext){
        User user = accountContext.returnUser();

        List<Image> imageList = imageService.getImageList(user.getId());
        log.info("========imageList={}",imageList);
        log.info("==============================================");
        for (Image image1 : imageList) {
            String thumbnailSaveName = image1.getThumbnailSaveName();
            log.info(thumbnailSaveName);
        }
        log.info("==============================================");
        model.addAttribute("image",image);
        model.addAttribute("imageList",imageList);
        model.addAttribute("accountContext",accountContext);

        return "myprofile";
    }

    @RequestMapping("/chat")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chat");
        return mv;
    }

    /**
     * 방 페이지
     * @return
     */
    @RequestMapping("/room")
    public ModelAndView room() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("room");
        return mv;
    }

    /**
     * 방 생성하기
     * @param params
     * @return
     */
    @RequestMapping("/createRoom")
    public @ResponseBody List<Room> createRoom(@RequestParam HashMap<Object, Object> params){
        String roomName = (String) params.get("roomName");
        if(roomName != null && !roomName.trim().equals("")) {
            Room room = new Room();
            room.setRoomNumber(++roomNumber);
            room.setRoomName(roomName);
            roomList.add(room);
        }
        return roomList;
    }

    /**
     * 방 정보가져오기
     * @param params
     * @return
     */
    @RequestMapping("/getRoom")
    public @ResponseBody List<Room> getRoom(@RequestParam HashMap<Object, Object> params){
        return roomList;
    }

    /**
     * 채팅방
     * @return
     */
    @RequestMapping("/moveChating")
    public ModelAndView chating(@RequestParam HashMap<Object, Object> params) {
        ModelAndView mv = new ModelAndView();
        int roomNumber = Integer.parseInt((String) params.get("roomNumber"));

        List<Room> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
        if(new_list != null && new_list.size() > 0) {
            mv.addObject("roomName", params.get("roomName"));
            mv.addObject("roomNumber", params.get("roomNumber"));
            mv.setViewName("chat");
        }else {
            mv.setViewName("room");
        }
        return mv;
    }

    @GetMapping("/getmap")
    public String getMap(Model model,@AuthenticationPrincipal AccountContext accountContext){
        model.addAttribute("accountContext",accountContext);
        return "/getmap";
    }



    @GetMapping("/credit")
    public String credit(){
        return "/credit";
    }
}
