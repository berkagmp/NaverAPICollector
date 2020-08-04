package com.berkagmp.app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = true)
@Entity
@Table(name = "items")
public class Item implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "p_id", nullable = false)
  private String pId;

  @Column(name = "p_name", nullable = false)
  private String title;

  @Column(name = "seller", nullable = false)
  private String mallName;

  @Column(nullable = false)
  private String link;

  @Column(name = "price", nullable = false)
  private Integer lprice;

  @Column(name = "delivery_fee", nullable = true)
  private Integer deliveryFee;

  @Column(name = "sum", nullable = true)
  private Integer sum;

  @Column(name = "active", nullable = false)
  private Boolean active = true;

  @Column(name = "created_at", updatable = false)
  @CreatedDate
  private Date created_at;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  public Item(String pId, String title, String mallName, String link, Integer lprice,
      Integer deliveryFee, Integer sum, Boolean active, Product product) {
    super();
    this.pId = pId;
    this.title = title;
    this.mallName = mallName;
    this.link = link;
    this.lprice = lprice;
    this.deliveryFee = deliveryFee;
    this.sum = sum;
    this.active = active;
    this.product = product;
  }

}
