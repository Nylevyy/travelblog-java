package ru.travelblog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "userdata")
@NoArgsConstructor
public class UserEntity extends AbstractEntity {
  private String username;
  private String password;
}
