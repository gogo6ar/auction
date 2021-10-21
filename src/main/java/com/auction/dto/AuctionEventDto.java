package com.auction.dto;

import com.auction.model.AuctionCharity;
import com.auction.model.AuctionEvent;
import com.auction.model.User;
import com.auction.model.enums.AuctionStatus;
import com.auction.model.enums.AuctionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuctionEventDto {
    private Long id;

    private String title;

    private String description;

    private AuctionStatus statusType;

    private AuctionType auctionType;

    private Double startPrice;

    private Double finishPrice;

    private User user;

    private Date startDate;

    private Date finishDate;

    private Date genDate;

    private Double charityPercent;

    public static AuctionEventDto from(AuctionEvent auctionEvent) {
        AuctionEventDto auctionEventDto = AuctionEventDto.builder()
                .id(auctionEvent.getId())
                .title(auctionEvent.getTitle())
                .description(auctionEvent.getDescription())
                .statusType(auctionEvent.getStatusType())
                .auctionType(auctionEvent.getAuctionType())
                .startPrice(auctionEvent.getStartPrice())
                .finishPrice(auctionEvent.getFinishPrice())
                .user(auctionEvent.getUser())
                .startDate(auctionEvent.getStartDate())
                .finishDate(auctionEvent.getFinishDate())
                .genDate(auctionEvent.getGenDate())
                .build();
        return auctionEventDto;
    }

    public static AuctionEventDto from(AuctionEvent auctionEvent,
                                       AuctionCharity auctionCharity) {
        AuctionEventDto auctionEventDto = from(auctionEvent);
        if (auctionCharity.getPercent() != null) {
            auctionEventDto.setCharityPercent(auctionCharity.getPercent());
        }
        return auctionEventDto;
    }
}