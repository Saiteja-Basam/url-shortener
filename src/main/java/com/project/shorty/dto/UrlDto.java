package com.project.shorty.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlDto {
	
	private String originalUrl;
	private String EncodedUrl;
	private LocalDateTime expirationTime;

}
