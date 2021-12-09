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

    private String introduce;

    private int like;

    private String x;

    private String y;

    @Builder
    public UserDto(Long id,String x,String y,int like,String introduce,String userImagePath,LocalDateTime createdTime, String username, String realname, String password, String sex, String street, String age, String role, String imageName){
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
        this.introduce=introduce;
        this.like=like;
        this.x=x;
        this.y=y;
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
                .introduce(introduce)
                .like(like)
                .x(x)
                .y(y)
                .build();
    }
}
