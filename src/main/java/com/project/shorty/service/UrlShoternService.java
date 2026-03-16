package com.project.shorty.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.project.shorty.dto.UrlDto;
import com.project.shorty.entity.UrlData;
import com.project.shorty.repository.UrlDataRepository;
import com.project.shorty.util.Base62Encoder;

@Service
public class UrlShoternService {
	
	@Autowired
	private UrlDataRepository urlDataRepository;
	
	public UrlDto createShortUrl(UrlDto urlDto) {
		
		Optional<UrlData> existing = urlDataRepository.findByOriginalUrl(urlDto.getOriginalUrl());

		if(existing.isPresent()){
			return UrlDto.builder()
		            .originalUrl(existing.get().getOriginalUrl())
		            .EncodedUrl(existing.get().getEncodedUrl())
		            .expirationTime(existing.get().getExpiryTime())
		            .build();
		}
		System.out.println("Fetching from DB...");
		LocalDateTime now = LocalDateTime.now();

	    UrlData urlData = new UrlData();
	    urlData.setOriginalUrl(urlDto.getOriginalUrl());
	    urlData.setCreatedAt(now);
	    urlData.setExpiryTime(now.plusDays(1));

	    urlData = urlDataRepository.save(urlData);

	    String shortCode = Base62Encoder.encode(urlData.getId());

	    urlData.setEncodedUrl(shortCode);
	    urlDataRepository.save(urlData);

	    return UrlDto.builder()
	            .originalUrl(urlData.getOriginalUrl())
	            .EncodedUrl(shortCode)
	            .expirationTime(urlData.getExpiryTime())
	            .build();
	}
	
	@Cacheable(value = "urls", key = "#encodedUrl")
	public String getOriginalUrl(String  encodedUrl) {
		System.out.println("Fetching from DB...");
		Optional<UrlData> urlData= urlDataRepository.findByEncodedUrl(encodedUrl);
		
		if(urlData.isEmpty()) {
			throw new RuntimeException("Requested URL is expired or not present.");
		}
		
		if(LocalDateTime.now().isAfter(urlData.get().getExpiryTime())) {
			urlDataRepository.delete(urlData.get());
			throw new RuntimeException("Requested URL is expired.");
		}
		return urlData.get().getOriginalUrl();
	}
}
