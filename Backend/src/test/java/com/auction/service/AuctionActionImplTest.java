package com.auction.service;

import com.auction.model.AuctionAction;
import com.auction.model.AuctionEvent;
import com.auction.model.enums.AuctionStatus;
import com.auction.model.mapper.Mapper;
import com.auction.repository.AuctionActionRepository;
import com.auction.repository.AuctionEventRepository;
import com.auction.service.interfaces.AuctionEventService;
import com.auction.service.interfaces.UserService;
import com.auction.web.dto.AuctionActionDto;
import com.auction.web.dto.AuctionEventDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuctionActionImplTest {

  @Mock
  private AuctionEventRepository auctionEventRepository;
  @Mock
  private AuctionActionRepository auctionActionRepository;
  @Mock
  private Mapper<AuctionAction, AuctionActionDto> auctionActionToDtoMapper;
  @Mock
  private AuctionEventService auctionEventService;
  @Mock
  private Mapper<AuctionEvent, AuctionEventDto> auctionEventDtoMapper;
  @Mock
  private UserService userService;

  private AuctionActionServiceImpl auctionActionService;

  private AuctionEvent auctionEvent;
  private AuctionAction auctionAction;

  @BeforeEach
  public void setUp() {
    auctionActionService = new AuctionActionServiceImpl(auctionActionRepository,
                                                        auctionEventRepository,
                                                        auctionEventService,
                                                        auctionActionToDtoMapper,
                                                        auctionEventDtoMapper,
                                                        userService);
    auctionEvent = AuctionEvent.builder()
            .title("title")
            .statusType(AuctionStatus.ACTIVE)
            .build();

    auctionAction = AuctionAction.builder()
            .auctionEvent(auctionEvent)
            .bet(10D)
            .build();
    auctionAction.setId(1000L);
  }

  @Test
  void checkBet_whenAuctionEventHasStatusExpectation_returnWrongBetExceptionWithMessageAboutStatus() {
    Double bet = 10D;
    auctionEvent.setStatusType(AuctionStatus.EXPECTATION);
    Exception exception = assertThrows(RuntimeException.class, () -> {
      auctionActionService.checkBet(auctionEvent, bet);
    });

    String expectedMessage = "has status";
    String actualMessage = exception.getMessage();

    assertThat(actualMessage.contains(expectedMessage));
  }

  @Test
  void checkBet_whenAuctionActionIsNullAndStartPriceMoreThanBet_returnWrongBetException() {
    when(auctionActionRepository.findTopByAuctionEventOrderByBetDesc(any(AuctionEvent.class))).thenReturn(Optional.empty());
    Double bet = 10D;
    auctionEvent.setStartPrice(20D);
    auctionEvent.setStatusType(AuctionStatus.ACTIVE);
    Exception exception = assertThrows(RuntimeException.class, () -> {
      auctionActionService.checkBet(auctionEvent, bet);
    });

    String expectedMessage = "Bet should be higher than Start Price!";
    String actualMessage = exception.getMessage();

    assertThat(actualMessage.contains(expectedMessage));
  }

  @Test
  void checkBet_whenLastBetIsHigherThanCurrentBet_returnWrongBetException() {
    auctionAction.setBet(10D);
    when(auctionActionRepository.findTopByAuctionEventOrderByBetDesc(any(AuctionEvent.class))).thenReturn(Optional.ofNullable(auctionAction));
    Double bet = 10D;
    auctionEvent.setStartPrice(5D);
    auctionEvent.setStatusType(AuctionStatus.ACTIVE);
    Exception exception = assertThrows(RuntimeException.class, () -> {
      auctionActionService.checkBet(auctionEvent, bet);
    });

    String expectedMessage = "Bet should be 5 percent higher!";
    String actualMessage = exception.getMessage();

    assertThat(actualMessage.contains(expectedMessage));
  }

  @Test
  void getAllByAuctionId_whenInvoked_returnListOfAuctionAction() {
    Long auctionId = 2000L;
    List<AuctionAction> auctionActionList = new ArrayList<>();
    auctionActionList.add(auctionAction);

    List<AuctionActionDto> dtos = new ArrayList<>();
    dtos.add(AuctionActionDto.builder()
                     .auctionEvent(auctionId)
                     .bid(10D)
                     .build());
    when(auctionActionRepository.findByAuctionEvent(any(AuctionEvent.class))).thenReturn(auctionActionList);
    when(auctionEventRepository.findById(auctionId)).thenReturn(Optional.ofNullable(auctionEvent));
    when(auctionActionToDtoMapper.mapList(any(List.class))).thenReturn(dtos);

    assertThat(auctionActionService.getAllByAuctionId(auctionId)).isNotEmpty();
  }

}
