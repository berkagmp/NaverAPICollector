package com.berkagmp.app.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product extends AudiableEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "active", nullable = false)
  private Boolean active = true;

  @Column(name = "keyword", nullable = false, length = 100)
  private String keyword;

  @Column(name = "raw")
  private Float raw;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id", nullable = false)
  @JsonBackReference
  private Brand brand;

  @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<Item> items;

  public Product(String name, Boolean active, String keyword, Float raw, Brand brand) {
    super();
    this.name = name;
    this.active = active;
    this.keyword = keyword;
    this.raw = raw;
    this.brand = brand;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", active=" + active + ", keyword=" + keyword
        + ", raw=" + raw + ", brand_id=" + brand.getId() + "]";
  }

}
