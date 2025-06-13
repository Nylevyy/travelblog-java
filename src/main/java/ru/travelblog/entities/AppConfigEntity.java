package ru.travelblog.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "application_config")
@NoArgsConstructor
public class AppConfigEntity extends AbstractEntity {
  @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @EqualsAndHashCode.Exclude
  private UserEntity user;

  @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "appConfig")
  @JsonManagedReference
  private BlogConfigEntity blogConfig;
}
