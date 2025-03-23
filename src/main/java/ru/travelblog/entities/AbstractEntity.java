package ru.travelblog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class AbstractEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private UUID id;

  @CreationTimestamp
  @Column(updatable = false)
  private Timestamp createdAt;
}
