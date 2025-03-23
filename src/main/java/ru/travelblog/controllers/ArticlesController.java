package ru.travelblog.controllers;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.travelblog.controllers.dto.ErrorResponseDto;
import ru.travelblog.controllers.dto.GetArticlesResponse;
import ru.travelblog.controllers.dto.HttpResponseDto;
import ru.travelblog.controllers.dto.SaveArticleRequest;
import ru.travelblog.controllers.dto.SaveArticleResponse;
import ru.travelblog.domain.Article;
import ru.travelblog.dto.ArticleDto;
import ru.travelblog.services.ArticleService;

@RestController
@RequestMapping("/api/articles")
@Slf4j
@RequiredArgsConstructor
public class ArticlesController {
  private final ArticleService articleService;
  private final ModelMapper mapper = new ModelMapper();

  @GetMapping
  public HttpResponseDto<GetArticlesResponse> getArticlesByAuthorizedUser() {
    HttpResponseDto<GetArticlesResponse> res = new HttpResponseDto<>();
    try {
      UUID userId = getUserIdFromRequest();
      List<Article> articles = articleService.getUserArticles(userId);

      GetArticlesResponse responseData = new GetArticlesResponse();
      responseData.setArticles(
        articles
          .stream()
          .map(a -> mapper.map(a, ArticleDto.class))
          .toList()
      );
      res.setData(responseData);
      return res;
    } catch (Exception e) {
      log.error("[getArticlesByAuthorizedUser] failed with {}", e);
      ErrorResponseDto error = new ErrorResponseDto();
      error.setMessage(e.getMessage());
      error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      res.setError(error);
      return res;
    }
  }

  @PostMapping
  public HttpResponseDto<SaveArticleResponse> createArticleForAuthorizedUser(
      @RequestBody SaveArticleRequest requestBody) {
    HttpResponseDto<SaveArticleResponse> res = new HttpResponseDto<>();

    try {
      UUID userId = getUserIdFromRequest();
      Article article = mapper.map(requestBody, Article.class);
      articleService.createArticleForUser(article, userId);
      List<Article> articles = articleService.getUserArticles(userId);

      SaveArticleResponse responseData = new SaveArticleResponse();
      responseData.setArticles(
          articles
              .stream()
              .map(a -> mapper.map(a, ArticleDto.class))
              .toList());
      res.setData(responseData);
      return res;
    } catch (Exception e) {
      log.error("[createArticleForAuthorizedUser] failed with {}", e);
      ErrorResponseDto error = new ErrorResponseDto();
      error.setMessage(e.getMessage());
      error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      res.setError(error);
      return res;
    }
  }
  
  private UUID getUserIdFromRequest() {
    // TODO: logic for retrieving user id from jwt
    return UUID.fromString("d322eafd-c2fd-4a9f-9ee1-60d3ab2c8a73");
  }
}
