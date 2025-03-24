package hoang.tran.test.repostitory;

import hoang.tran.test.model.UserKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserKeyRepository extends JpaRepository<UserKey, Long> {
    Optional<UserKey> findByUsernameAndCode(String username, String code);

    @Query(value = "SELECT * FROM user_keys " +
            "WHERE (:username IS NULL OR username = :username) " +
            "ORDER BY created_date DESC",
            nativeQuery = true)
    Page<UserKey> findByUserKey(@Param("username") String username, Pageable pageable);

    long countByUsername(String username);
}
