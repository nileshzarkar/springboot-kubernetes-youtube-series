package com.sivalabs.bookmarker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sivalabs.bookmarker.dto.BookmarkDTO;
import com.sivalabs.bookmarker.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	
	@Query("select new com.sivalabs.bookmarker.dto.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b")
	Page<BookmarkDTO> findBookmarks(Pageable pageable);
	

}
