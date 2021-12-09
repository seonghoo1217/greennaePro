package dongyang.ac.kr.greennaePro.domain;

import dongyang.ac.kr.greennaePro.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class User {

   public User(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name",unique = true)
    private String username;

    @Column(name = "user_realname")
    private String realname;

    @Column(name = "user_pw")
    private String password;

    @Column(name = "user_age")
    private String age;

    @Column(name = "user_street")
    private String street;

    @Column(name = "user_sex")
    private String sex;

    @Column(name = "user_role")
    private String role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Board> boards=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Image> fileImage=new ArrayList<>();

    @Column(name = "user_profile")
    private String imageName;

    @CreatedDate
    private LocalDateTime createdTime;

    @Column(name = "user_img")
    private String userImagePath;

    @Column(name = "user_introduce")
    private String introduce;

    @Column(name = "user_like")
    private int like;

    @Column(name = "user_x")
    private String x;

    @Column(name = "user_y")
    private String y;



    @Builder
    public User(Long id,String x,String y,int like,String introduce,String userImagePath,LocalDateTime createdTime, String username, String realname, String password, String age, String street, String sex, String role, String imageName) {
        this.id=id;
        this.username=username;
        this.realname=realname;
        this.password=password;
        this.age=age;
        this.sex=sex;
        this.street=street;
        this.role=role;
        this.imageName=imageName;
        this.createdTime=createdTime;
        this.userImagePath=userImagePath;
        this.introduce=introduce;
        this.like=like;
        this.x=x;
        this.y=y;
    }


    public UserDto toDto(){
        return UserDto.builder()
                .id(id)
                .username(username)
                .realname(realname)
                .password(password)
                .age(age)
                .sex(sex)
                .street(street)
                .role(role)
                .userImagePath(userImagePath)
                .imageName(null)
                .createdTime(createdTime)
                .introduce(introduce)
                .like(like)
                .x(x)
                .y(y)
                .build();
    }
}
