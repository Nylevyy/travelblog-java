package ru.travelblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUserConfigResponse {
  private AppConfigDto applicationConfig;
}
