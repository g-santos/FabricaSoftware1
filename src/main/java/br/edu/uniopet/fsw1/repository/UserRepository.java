package br.edu.uniopet.fsw1.repository;

import br.edu.uniopet.fsw1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM T_USER WHERE (USERNAME = :username OR EMAIL = :email) ", nativeQuery = true)
    Optional<User> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByEmailOrUsername(String email, String username);
}
