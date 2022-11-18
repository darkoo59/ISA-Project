package bloodcenter.person.repository;

import bloodcenter.person.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value="SELECT u FROM user_ u WHERE LOWER(CONCAT(u.firstname, ' ', u.lastname)) LIKE %?1% OR LOWER(CONCAT(u.lastname, ' ', u.firstname)) LIKE %?1%")
    List<User> searchUsers(String searchInput);
}
