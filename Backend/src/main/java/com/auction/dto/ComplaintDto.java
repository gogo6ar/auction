package com.auction.dto;

import com.auction.model.AuctionEvent;
import com.auction.model.AuctionEventComplaint;
import com.auction.model.AuctionEventComplaintAudit;
import com.auction.model.User;
import com.auction.model.enums.ComplaintStatus;
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
public class ComplaintDto {
  private Long id;
  private AuctionEventDto auctionEvent;
  private UserDto user;
  private UserDto admin;
  private String message;
  private ComplaintStatus status;
  private Date genDate;

  public static ComplaintDto from (AuctionEventComplaint complaint,
                                   AuctionEventComplaintAudit complaintAudit) {
    ComplaintDto complaintDto = from(complaint);
    UserDto admin = UserDto.from(complaintAudit.getUser());
    complaintDto.setAdmin(admin);

    return complaintDto;
  }

  public static ComplaintDto from (AuctionEventComplaint complaint) {
    ComplaintDto complaintDto = ComplaintDto.builder()
            .id(complaint.getId())
            .user(UserDto.from(complaint.getUser()))
            .auctionEvent(AuctionEventDto.from(complaint.getAuctionEvent()))
            .genDate(complaint.getGenDate())
            .message(complaint.getMessage())
            .status(complaint.getStatus())
            .build();

    return complaintDto;
  }
}
