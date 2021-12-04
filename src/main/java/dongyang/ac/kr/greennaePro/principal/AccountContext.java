package dongyang.ac.kr.greennaePro.principal;


import dongyang.ac.kr.greennaePro.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AccountContext implements UserDetails {


    private Long id;
    private String username;
    private String password;
    private String role;
    private String street;
    private String realname;
    private String sex;
    private String imageName;
    private String age;


    public AccountContext(User user) {
        this.id= user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.realname=user.getRealname();
        this.role = user.getRole();
        this.sex= user.getSex();
        this.age=user.getAge();
        this.street= user.getStreet();
        this.imageName= user.getImageName();
    }

    public AccountContext() {

    }

    public User returnUser(){

        User buildUser = User.builder()
                .id(id)
                .username(username)
                .realname(realname)
                .sex(sex)
                .age(age)
                .street(street)
                .password(password)
                .role(role)
                .build();

        return buildUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getImageName(){
        return this.imageName;
    }
    public String getStreet(){
        return this.street=street;
    }
    public String getSex(){
        return this.sex=sex;
    }
    public String getRealname(){return this.realname=realname;}

    public String getAge(){return this.age=age;}

    public Long getId(){return this.id;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //계정비밀번호만료여부
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    //계정활성화여부
    public boolean isEnabled() {
        return true;
    }
}
