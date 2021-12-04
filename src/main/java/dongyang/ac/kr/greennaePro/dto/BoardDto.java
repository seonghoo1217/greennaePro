package dongyang.ac.kr.greennaePro.dto;

import dongyang.ac.kr.greennaePro.domain.Board;
import dongyang.ac.kr.greennaePro.domain.Image;
import dongyang.ac.kr.greennaePro.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {


    private Long id;
    private String title;
    private String writer;
    private String content;
    private User user;
    private Image image;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    @Builder
    public BoardDto(Long id, String title,LocalDateTime createdDate,LocalDateTime modifiedDate, String writer,Image image, String content, User user){
        this.id=id;
        this.title=title;
        this.writer=writer;
        this.content=content;
        this.user=user;
        this.createdDate=createdDate;
        this.modifiedDate=modifiedDate;
        this.image=image;
        this.user=user;
    }



    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .user(user)
                .createDate(createdDate)
                .modifiedDate(modifiedDate)
                .image(image)
                .build();
    }


}
