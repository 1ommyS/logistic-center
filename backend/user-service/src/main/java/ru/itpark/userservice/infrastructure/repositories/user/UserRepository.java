package ru.itpark.userservice.infrastructure.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.userservice.domain.user.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (full_name, email, login, languages, role, created_at, deleted_at) " +
            "VALUES (:fullName, :email, :login, cast(:languages as jsonb), :role, :createdAt,  :deletedAt)", nativeQuery = true)
    void saveUser(@Param("fullName") String fullName,
                  @Param("email") String email,
                  @Param("login") String login,
                  @Param("languages") String languages,
                  @Param("role") String role,
                  @Param("createdAt") LocalDateTime createdAt,
                  @Param("deletedAt") LocalDateTime deletedAt);

}