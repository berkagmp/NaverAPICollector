package com.berkagmp.app.entity;

import java.util.List;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class Result {

  private String lastBuildDate;
  private String total;
  private String start;
  private String display;

  private List<Item> items;

}
