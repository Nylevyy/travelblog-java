package ru.travelblog.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "blog_config")
@NoArgsConstructor
public class BlogConfigEntity extends AbstractEntity {
  private String title;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "application_config_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference
  @EqualsAndHashCode.Exclude
  private AppConfigEntity appConfig;
}
