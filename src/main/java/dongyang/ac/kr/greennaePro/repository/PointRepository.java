package dongyang.ac.kr.greennaePro.repository;

import dongyang.ac.kr.greennaePro.domain.Point;
import dongyang.ac.kr.greennaePro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point,Long> {

    List<Point> findAllByUser(User user);

    @Transactional
    @Query("select sum(p.amount) from Point p where p.user=?1")
    Long amountSum(User user);
}
