package dongyang.ac.kr.greennaePro.dto;

import dongyang.ac.kr.greennaePro.domain.Point;
import dongyang.ac.kr.greennaePro.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PointDto {

    private Long id;
    private Long amount;
    private LocalDateTime createdTime;
    private User user;

    @Builder
    public PointDto(Long id, Long amount,LocalDateTime createdTime,User user){
        this.id=id;
        this.amount=amount;
        this.createdTime=createdTime;
        this.user=user;
    }

    public PointDto(Long amount) {
    }

    public Point toEntity(){
        return Point.builder()
                .id(id)
                .amount(amount)
                .createdTime(createdTime)
                .user(user)
                .build();
    }
}
