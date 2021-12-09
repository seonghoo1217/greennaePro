package dongyang.ac.kr.greennaePro.service;

import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.dto.UserDto;
import dongyang.ac.kr.greennaePro.principal.AccountContext;
import dongyang.ac.kr.greennaePro.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public Long createUser  (UserDto dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        if(dto.getUsername().equals("dongyang1234")){
            dto.setRole("ROLE_ADMIN");
        }else {
            dto.setRole("ROLE_USER");
        }


        dto.setCreatedTime(LocalDateTime.now());
        dto.setLike(0);
        log.info("sibal={}", dto.toEntity().getFileImage());
        return userRepository.save(dto.toEntity()).getId();
    }
    @Transactional
    public int findById(Long id){
        User findUser = userRepository.findById(id).get();
        if(findUser == null){
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();

        return new AccountContext(user);
    }

    @Transactional
    public void UserUpdate(Long id,String fileName){
        User user = userRepository.findById(id).get();
        user.setImageName(fileName);
    }
    @Transactional
    public void UserSetImage(Long id,String userImagePath){
        User user = userRepository.findById(id).get();
        user.setUserImagePath(userImagePath);
    }

    @Transactional
    public void updateUserLike(Long id,int like){
        User user = userRepository.findById(id).get();
        user.setLike(user.getLike()+1);
    }

    @Transactional
    public void updateXY(Long id,String x,String y){
        User user = userRepository.findById(id).get();
        log.info("this is service x={}",x.getClass().getName());
        log.info("this is service y={}",y.getClass().getName());

        user.setX(x);
        user.setY(y);
    }

}
