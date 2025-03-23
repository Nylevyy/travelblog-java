package ru.travelblog.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
@NoArgsConstructor
public class ArticleEntity extends AbstractEntity {
  private String title;
  private String description;
  private String location;
  private Timestamp date;
  private Boolean isImportant;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @EqualsAndHashCode.Exclude
  private UserEntity user;
}
