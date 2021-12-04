package dongyang.ac.kr.greennaePro.repository.user;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dongyang.ac.kr.greennaePro.domain.User;
import dongyang.ac.kr.greennaePro.dto.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static dongyang.ac.kr.greennaePro.domain.QImage.image;
import static dongyang.ac.kr.greennaePro.domain.QUser.user;

public class UserCustomRepositoryImpl implements UserCustomRepository{

    private final JPAQueryFactory queryFactory;

    public UserCustomRepositoryImpl(EntityManager em){
        this.queryFactory=new JPAQueryFactory(em);
    }


    @Override
    public PageImpl<User> userpaging(Long id, Pageable pageable) {
        QueryResults<User> userResults = queryFactory
                .selectFrom(user)
                .leftJoin(image).on(image.user.id.eq(id))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(user.createdTime.desc())
                .fetchResults();

        List<UserDto> ListUserResult = userResults.getResults().stream().map(User::toDto).collect(Collectors.toList());

        long total = userResults.getTotal();

        return new PageImpl(ListUserResult, pageable, total);
    }
}
