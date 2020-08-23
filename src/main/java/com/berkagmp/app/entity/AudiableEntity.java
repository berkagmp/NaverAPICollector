package com.berkagmp.app.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AudiableEntity {

  @Column(nullable = false, name = "created_at", updatable = false)
  @CreatedDate
  private Date createdAt;

  @Column(nullable = false, name = "updated_at")
  @LastModifiedDate
  private Date updatedAt;

}
