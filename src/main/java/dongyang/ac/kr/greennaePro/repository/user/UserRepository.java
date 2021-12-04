package dongyang.ac.kr.greennaePro.repository.user;

import dongyang.ac.kr.greennaePro.domain.User;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>,UserCustomRepository {
    Optional<User> findByUsername (String username) throws UsernameNotFoundException;

    @Query("select u from User u")
    List<User> findAll();

    PageImpl<User> userpaging(Long id, Pageable pageable);
}
