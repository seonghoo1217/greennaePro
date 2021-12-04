package dongyang.ac.kr.greennaePro.dto;

import dongyang.ac.kr.greennaePro.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String sex;

    private String age;

    private String realname;

    private String street;

    private String role;

    private String imageName;

    private LocalDateTime createdTime;

    private String userImagePath;


    @Builder
    public UserDto(Long id,String userImagePath,LocalDateTime createdTime, String username, String realname, String password, String sex, String street, String age, String role, String imageName){
        this.id=id;
        this.username=username;
        this.realname=realname;
        this.password=password;
        this.sex=sex;
        this.age=age;
        this.street=street;
        this.role=role;
        this.imageName=imageName;
        this.userImagePath=userImagePath;
        this.createdTime=createdTime;
    }



    public User toEntity(){
        return User.builder()
                .id(id)
                .username(username)
                .realname(realname)
                .password(password)
                .age(age)
                .sex(sex)
                .street(street)
                .role(role)
                .createdTime(createdTime)
                .userImagePath(userImagePath)
                .imageName(null)
                .build();
    }
}