package com.auction.repository;

import com.auction.model.TokenConfirmation;
import com.auction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenConfirmationRepository extends JpaRepository<TokenConfirmation, Long> {
  Optional<TokenConfirmation> findByUser(User user);

  Optional<TokenConfirmation> findByConfirmation(String confirmation);

  void delete(TokenConfirmation confirmation);

  void deleteAllByUser(User user);

  List<TokenConfirmation> findAllByGenDateLessThan(LocalDateTime localDateTime);
}
