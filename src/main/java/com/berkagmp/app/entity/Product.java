package com.berkagmp.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

	@Column(name = "brand_id", nullable = false)
	private Integer brandId;

	public Product(String name, Boolean active, String keyword, Float raw, Integer brandId) {
		super();
		this.name = name;
		this.active = active;
		this.keyword = keyword;
		this.raw = raw;
		this.brandId = brandId;
	}
}
