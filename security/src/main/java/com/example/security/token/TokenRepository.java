package com.example.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("SELECT t from Token AS t INNER JOIN User AS u ON t.user.id = u.id " +
            "WHERE u.id = ?1 AND (t.expired = false OR t.revoked = false)")
    List<Token> findAllValidTokensByUser(Integer userId);

    Optional<Token> findByToken(Integer token);
}
