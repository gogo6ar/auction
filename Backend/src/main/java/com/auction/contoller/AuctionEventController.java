package com.auction.contoller;

import com.auction.dto.AuctionEventDto;
import com.auction.dto.request.AuctionEventRequest;
import com.auction.exception.AuctionEventNotFoundException;
import com.auction.service.interfaces.AuctionEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/auction")
public class AuctionEventController {

    private final AuctionEventService auctionEventService;

    @PostMapping()
    public ResponseEntity createAuctionEvent(@RequestBody AuctionEventRequest request) {
        AuctionEventDto auctionEventDto = auctionEventService.save(request);

        return ResponseEntity.ok(auctionEventDto);
    }

    @GetMapping()
    public List<AuctionEventDto> getAll() {
        return auctionEventService.getAll();
    }

    @GetMapping("/sort")
    public List<AuctionEventDto> getAllAuctionByRating() {
        return auctionEventService.getAllSortByRating();
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return "Hello, " + HtmlUtils.htmlEscape(message + "xq");
    }

    @DeleteMapping()
    public ResponseEntity deleteById(Long auctionId) throws AuctionEventNotFoundException {
        auctionEventService.delete(auctionId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{auctionId}")
    public ResponseEntity updateById(@PathVariable Long auctionId,
                                     @RequestBody AuctionEventRequest request) throws AuctionEventNotFoundException {
        AuctionEventDto auctionEventDto = auctionEventService.update(request ,auctionId);
        return ResponseEntity.ok(auctionEventDto);
    }
}
