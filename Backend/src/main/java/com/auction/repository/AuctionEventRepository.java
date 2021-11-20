package com.auction.repository;

import com.auction.web.model.AuctionEvent;
import com.auction.web.model.User;
import com.auction.web.model.enums.AuctionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionEventRepository extends JpaRepository<AuctionEvent, Long> {

  AuctionEvent save(AuctionEvent auctionEvent);

  Optional<AuctionEvent> findById(Long id);

  @Query(nativeQuery = true, value =
          "select * from auction as a " +
                  "where a.status = status and a.start_date <= LOCALTIMESTAMP")
  List<AuctionEvent> getListForStartOrFinish(@Param("status") String status);

  @Query(nativeQuery = true, value =
          "select a.id, " +
                  "(select count(*) from auction_action aa where aa.auction_id = a.id) as count " +
                  "from auction AS a " +
                  "where a.status != 'BLOCKED' " +
                  "order by count")
  List<Object[]> getAuctionEventForSorting();

  @Query(nativeQuery = true, value =
          "SELECT a.* FROM auction AS a " +
                  "LEFT JOIN auction_sort AS aa ON a.id = aa.auction_id " +
                  "WHERE a.status != 'BLOCKED' " +
                  "ORDERD BY aa.rating")
  List<AuctionEvent> getAuctionEventByRating();

  List<AuctionEvent> findByUser(User user);

  @Query(nativeQuery = true, value =
        "SELECT a.* FROM auction AS a " +
        "WHERE a.status = 'ACTIVE' OR " +
        "a.status = 'FINISHED' AND " +
        "a.title LIKE search " +
        "LIMIT rows")
  List<AuctionEvent> search(@Param("search") String message,
                            @Param("rows") int limit);
}
