package ru.travelblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import ru.travelblog.entities.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
  Optional<RefreshToken> findByToken(String token);
  Optional<RefreshToken> findByTokenAndExpiresAtBefore(String token, Instant date);
  Optional<RefreshToken> findByUserId(Long userId);
  Optional<RefreshToken> findByIdAndExpiresAtAfter(UUID id, Instant date);

  void deleteByToken(String token);
}
