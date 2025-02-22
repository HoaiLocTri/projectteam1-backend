package fpt.intern.fa_api.repository;

import fpt.intern.fa_api.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "select * from user where email = ?1 or username = ?1", nativeQuery = true)
    Optional<User> findByEmailOrUsername(String username);

    Optional<User> findByEmail(String email);
}
