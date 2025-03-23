package ru.travelblog.dto;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
  private UUID id;
  private String title;
  private String description;
  private String location;
  private Timestamp date;
  private Boolean isImportant;
  private UUID userId;
}
