package com.project.shorty.controller;
import com.project.shorty.service.UrlShoternService;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.shorty.dto.UrlDto;

@RestController
public class UrlShorternController {
	
	@Autowired
    private UrlShoternService urlShoternService;

	
	@PostMapping("/getEncodedUrl")
	ResponseEntity<UrlDto> getEncodedUrl(@RequestBody UrlDto urlDto)
	{
			return ResponseEntity.ok(urlShoternService.createShortUrl(urlDto));
	}
	
	 @GetMapping("/{encodedUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String encodedUrl) {

        String originalUrl = urlShoternService.getOriginalUrl(encodedUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
