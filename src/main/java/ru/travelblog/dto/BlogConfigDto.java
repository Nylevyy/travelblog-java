package ru.travelblog.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BlogConfigDto {
  private UUID id;
  private String title;
}
