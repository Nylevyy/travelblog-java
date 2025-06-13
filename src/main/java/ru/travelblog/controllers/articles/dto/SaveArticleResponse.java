package ru.travelblog.controllers.articles.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.travelblog.dto.ArticleDto;

@Setter
@Getter
@NoArgsConstructor
public class SaveArticleResponse {
  private List<ArticleDto> articles;
}
