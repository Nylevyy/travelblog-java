package ru.travelblog.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class GetArticlesResponse {
  private List<ArticleDto> articles;
}
