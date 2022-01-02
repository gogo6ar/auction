package com.auction.repository;

import com.auction.model.AuctionEvent;
import com.auction.model.AuctionWinner;
import com.auction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionWinnerRepository extends JpaRepository<AuctionWinner, Long> {
  Optional<AuctionWinner> findByAuctionEvent(AuctionEvent auctionEvent);

  List<AuctionWinner> findByUser(User user);
}
