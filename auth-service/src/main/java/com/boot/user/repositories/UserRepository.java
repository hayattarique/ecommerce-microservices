package com.boot.user.repositories;

import com.boot.user.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.email = ?1 OR u.username = ?1")
    Optional<Users> findByEmail(@Param("email") String email);

    Optional<Users> findByUsername(String username);

    boolean existsByEmail(String email);
}
