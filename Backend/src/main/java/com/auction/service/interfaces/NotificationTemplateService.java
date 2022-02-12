package com.auction.service.interfaces;

import com.auction.model.AuctionEvent;
import com.auction.model.AuctionEventComplaintAudit;
import com.auction.model.AuctionWinner;

public interface NotificationTemplateService {
  void sendNotificationOfCreatingAuction(AuctionEvent auctionEvent);

  void sendNotificationOfFinishingAuction(AuctionWinner auctionWinner);

  void sendNotificationOfComplaintAnswer(AuctionEventComplaintAudit audit);
}
