package dongyang.ac.kr.greennaePro.domain;

import dongyang.ac.kr.greennaePro.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Lob
    @Column(name = "board_title")
    private String title;

    @Lob
    @Column(name = "board_content")
    private String content;

    @Column(name = "board_writer")
    private String writer;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedBy
    private LocalDateTime modifiedDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name ="image_id")
    private Image image;

    @Builder
    public Board(Long id, String title,User user,Image image, String content, String writer, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id=id;
        this.title=title;
        this.content=content;
        this.writer=writer;
        this.user=user;
        this.image=image;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;

    }

    public void update(String title,String content){
        this.title=title;
        this.content=content;
    }

    public BoardDto toDto(){
        return BoardDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .user(user)
                .createdDate(createDate)
                .modifiedDate(modifiedDate)
                .image(image)
                .build();
    }



}

