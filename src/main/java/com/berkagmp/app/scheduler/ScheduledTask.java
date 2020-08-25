package com.berkagmp.app.scheduler;

import com.berkagmp.app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

  private ItemService itemService;

  @Autowired
  ScheduledTask(ItemService itemService) {
    this.itemService = itemService;
  }

  // CRON: <second> <minute> <hour> <day-of-month> <month> <day-of-week> <year>
  @Scheduled(cron = "0 0 6 * * * ")
  public void batchCron() {
    itemService.collect();
  }
}
