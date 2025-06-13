package ru.travelblog.controllers.articles;

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
import ru.travelblog.controllers.articles.dto.GetArticlesResponse;
import ru.travelblog.controllers.articles.dto.SaveArticleRequest;
import ru.travelblog.controllers.articles.dto.SaveArticleResponse;
import ru.travelblog.controllers.dto.ErrorResponseDto;
import ru.travelblog.controllers.dto.HttpResponseDto;
import ru.travelblog.domain.Article;
import ru.travelblog.dto.ArticleDto;
import ru.travelblog.services.ArticleService;
import ru.travelblog.services.UserService;

@RestController
@RequestMapping("/api/articles")
@Slf4j
@RequiredArgsConstructor
public class ArticlesController {
  private final ArticleService articleService;
  private final UserService userService;
  private final ModelMapper mapper = new ModelMapper();

  @GetMapping
  public HttpResponseDto<GetArticlesResponse> getArticlesByAuthorizedUser() throws RuntimeException {
    HttpResponseDto<GetArticlesResponse> response = new HttpResponseDto<GetArticlesResponse>();

    try {
      UUID userId = getUserIdFromRequest();

      List<Article> articles = articleService.getUserArticles(userId);

      GetArticlesResponse responseData = new GetArticlesResponse();
      responseData.setArticles(
          articles
              .stream()
              .map(a -> mapper.map(a, ArticleDto.class))
              .toList());
      response.setData(responseData);
      return response;
    } catch (Exception e) {
      ErrorResponseDto errorResponse = new ErrorResponseDto();
      log.error("[getArticlesByAuthorizedUser] failed with {}", e);

      errorResponse.setMessage(e.getMessage());
      errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      response.setError(errorResponse);
      return response;
    }
  }

  @PostMapping
  public HttpResponseDto<SaveArticleResponse> createArticleForAuthorizedUser(
      @RequestBody SaveArticleRequest requestBody) throws RuntimeException {
    HttpResponseDto<SaveArticleResponse> response = new HttpResponseDto<SaveArticleResponse>();

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
      response.setData(responseData);
      return response;
    } catch (Exception e) {
      ErrorResponseDto errorResponse = new ErrorResponseDto();
      log.error("[createArticleForAuthorizedUser] failed with {}", e);

      errorResponse.setMessage(e.getMessage());
      errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      response.setError(errorResponse);
      return response;
    }
  }

  private UUID getUserIdFromRequest() {
    return userService.getCurrentUser().getId();
  }
}
