package dongyang.ac.kr.greennaePro.domain;

import dongyang.ac.kr.greennaePro.dto.PointDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_id")
    private Long id;

    @Column(name = "point_amount")
    private Long amount;

    @CreatedDate
    @Column(name = "point_date_time")
    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @Builder
    public Point(Long id,Long amount,LocalDateTime createdTime,User user){
        this.id=id;
        this.amount=amount;
        this.createdTime=createdTime;
        this.user=user;
    }

    private PointDto toDto(){
        return PointDto.builder()
                .id(id)
                .amount(amount)
                .createdTime(createdTime)
                .user(user)
                .build();

    }
}
