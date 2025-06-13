package ru.travelblog.controllers;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.travelblog.domain.AppConfig;
import ru.travelblog.domain.BlogConfig;
import ru.travelblog.dto.AppConfigDto;
import ru.travelblog.dto.BlogConfigDto;
import ru.travelblog.dto.ErrorResponseDto;
import ru.travelblog.dto.GetUserConfigResponse;
import ru.travelblog.dto.HttpResponseDto;
import ru.travelblog.dto.UpdateBlogConfigRequest;
import ru.travelblog.dto.UpdateBlogConfigResponse;
import ru.travelblog.services.AppConfigService;
import ru.travelblog.services.UserService;

@RestController
@RequestMapping("/api/userConfig")
@Slf4j
@RequiredArgsConstructor
public class UserConfigController {
    private final UserService userService;
    private final AppConfigService appConfigService;
    private final ModelMapper mapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<HttpResponseDto<GetUserConfigResponse>> getUserConfig() {
        HttpResponseDto<GetUserConfigResponse> response = new HttpResponseDto<GetUserConfigResponse>();

        try {
            UUID userId = getUserIdFromRequest();

            AppConfig appConfig = appConfigService.getUserAppConfig(userId);

            GetUserConfigResponse responseData = new GetUserConfigResponse();
            responseData.setApplicationConfig(mapper.map(appConfig, AppConfigDto.class));
            response.setData(responseData);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            log.error("[getUserConfig] failed with %s", e);

            errorResponse.setMessage(e.getMessage());
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setError(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PutMapping("/blog/{configId}")
    public ResponseEntity<HttpResponseDto<UpdateBlogConfigResponse>> updateBlogConfig(
            @PathVariable(name = "configId") UUID configId,
            @RequestBody UpdateBlogConfigRequest requestBody) {
        HttpResponseDto<UpdateBlogConfigResponse> response = new HttpResponseDto<UpdateBlogConfigResponse>();

        try {
            UUID userId = getUserIdFromRequest();

            BlogConfig blogConfig = mapper.map(requestBody, BlogConfig.class);
            blogConfig.setId(configId);

            BlogConfig updatedBlogConfig = appConfigService.saveUserBlogConfig(userId, blogConfig);

            UpdateBlogConfigResponse responseData = new UpdateBlogConfigResponse();
            responseData.setBlogConfigDto(mapper.map(updatedBlogConfig, BlogConfigDto.class));
            response.setData(responseData);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            log.error("[updateBlogConfig] failed with %s", e);

            errorResponse.setMessage(e.getMessage());
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setError(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
        }
    }

    private UUID getUserIdFromRequest() {
        return userService.getCurrentUser().getId();
    }
}
