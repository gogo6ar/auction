package com.auction.web.contoller;

import com.auction.service.interfaces.AuctionEventService;
import com.auction.web.dto.AuctionEventDto;
import com.auction.web.dto.request.AuctionEventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auction")
public class AuctionEventController {

    private final AuctionEventService auctionEventService;

    @PostMapping
    public ResponseEntity<AuctionEventDto> createAuctionEvent(@RequestBody @Valid AuctionEventRequest request) {
        return ResponseEntity.ok(auctionEventService.save(request));
    }

    @GetMapping
    public ResponseEntity<Page<AuctionEventDto>> getAll(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10") int perPage) {
        return ResponseEntity.ok(auctionEventService.get(page, perPage));
    }

    @GetMapping("/sort")
    public ResponseEntity<Page<AuctionEventDto>> getAllAuctionByRating(@RequestParam(defaultValue = "1") int page,
                                                                       @RequestParam(defaultValue = "10") int perPage) {
        return ResponseEntity.ok(auctionEventService.getAllSortByRating(page, perPage));
    }

    @DeleteMapping("/{auctionId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long auctionId) {
        auctionEventService.deleteById(auctionId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{auctionId}")
    public ResponseEntity<AuctionEventDto> updateById(@PathVariable Long auctionId,
                                                      @RequestBody AuctionEventRequest request) {
        return ResponseEntity.ok(auctionEventService.update(request ,auctionId));
    }

    @PutMapping("/block/{auctionId}")
    public ResponseEntity<AuctionEventDto> block(@PathVariable Long auctionId) {
        return ResponseEntity.ok(auctionEventService.blockAuctionEventById(auctionId));
    }
}
