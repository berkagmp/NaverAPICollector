package com.berkagmp.app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author berkagmp
 *
 */
/**
 * @author berkagmp
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "items")
@EntityListeners(AuditingEntityListener.class)
public class Item implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "p_id", nullable = false)
  @SerializedName("productId")
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
  private Integer deliveryFee = 0;

  @Column(name = "sum", nullable = true)
  private Integer sum = 0;

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

  @Override
  public String toString() {
    return "Item [id=" + id + ", pId=" + pId + ", title=" + title + ", mallName=" + mallName
        + ", link=" + link + ", lprice=" + lprice + ", deliveryFee=" + deliveryFee + ", sum=" + sum
        + ", active=" + active + ", created_at=" + created_at + ", product_id=" + product.getId()
        + "]";
  }

}
