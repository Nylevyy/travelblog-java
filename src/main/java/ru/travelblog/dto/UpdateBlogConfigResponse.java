package ru.travelblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateBlogConfigResponse {
  private BlogConfigDto blogConfig;
}
