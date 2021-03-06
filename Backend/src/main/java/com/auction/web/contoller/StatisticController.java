package com.auction.web.contoller;

import com.auction.service.interfaces.StatisticService;
import com.auction.web.dto.request.StatisticRequest;
import com.auction.web.dto.response.statistic.Statistic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statistic")
public class StatisticController {
  private final StatisticService statisticService;

  @GetMapping
  public ResponseEntity<Statistic> getStatistic() {
    return ResponseEntity.ok(statisticService.getStatistic());
  }

  @PostMapping
  public ResponseEntity<Statistic> getStatisticByFilter(@RequestBody StatisticRequest request) {
    return ResponseEntity.ok(statisticService.getStatisticByFilter(request.getTypes()));
  }
}
