package com.project.shorty.repository;

import com.project.shorty.entity.UrlData;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlDataRepository extends JpaRepository<UrlData, Long>{
	
	 Optional<UrlData> findByEncodedUrl(String encodedUrl);
	 
	 Optional<UrlData> findByOriginalUrl(String encodedUrl);

}
