package dongyang.ac.kr.greennaePro.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalFileName;

    private String fileName;

    private String uuid;

    private String folderPath;

    private String thumbnailSaveName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Image image = (Image) o;

        return Objects.equals(id, image.id);
    }

    @Override
    public int hashCode() {
        return 548781654;
    }

    @Builder
    public Image (Long id, String fileName ,String uuid, String originalFileName, String folderPath, User user, String thumbnailSaveName){
        this.id=id;
        this.fileName=fileName;
        this.originalFileName=originalFileName;
        this.uuid=uuid;
        this.folderPath=folderPath;
        this.thumbnailSaveName=thumbnailSaveName;
        this.user = user;
    }
}
