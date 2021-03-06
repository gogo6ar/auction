package com.auction.repository;

import com.auction.model.AuctionAction;
import com.auction.model.AuctionEvent;
import com.auction.model.User;
import com.auction.projection.LastBidProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionActionRepository extends JpaRepository<AuctionAction, Long> {

  Optional<AuctionAction> findTopByAuctionEventOrderByBetDesc(AuctionEvent auctionEvent);

  List<AuctionAction> findByAuctionEvent(AuctionEvent auctionEvent);

  @Query(nativeQuery = true, value = "" +
          "SELECT * FROM auction_action aa " +
          "WHERE aa.user_id = :userId " +
          "GROUP BY aa.auction_id, " +
          "         aa.id ")
  Page<AuctionAction> findAuctionActionByParticipantAndGroupByAuction(@Param("userId") Long userId, Pageable pageable);

  @Query(nativeQuery = true, value =
          "SELECT * FROM auction_action AS aa " +
          "WHERE aa.auction_id = :auctionId " +
          "GROUP BY aa.id")
  List<AuctionAction> getAllByAuctionGroupByUser(@Param("auctionId") Long auctionId);

  @Query(nativeQuery = true, value = "" +
          "SELECT MAX(aa.bet) AS lastBid, " +
          "aa.auction_id AS auctionId " +
          "FROM auction_action aa " +
          "WHERE aa.auction_id in :auctionIds " +
          "GROUP BY aa.auction_id ")
  List<LastBidProjection> getLastBidByAuctionIds(List<Long> auctionIds);

  void deleteAllByAuctionEvent(AuctionEvent auctionEvent);

  void deleteAllByUser(User user);
}
