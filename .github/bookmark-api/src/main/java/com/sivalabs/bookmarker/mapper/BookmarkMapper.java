package com.sivalabs.bookmarker.mapper;

import org.springframework.stereotype.Component;

import com.sivalabs.bookmarker.dto.BookmarkDTO;
import com.sivalabs.bookmarker.entity.Bookmark;

@Component
public class BookmarkMapper {
	public BookmarkDTO toDTO(Bookmark bookmark) {
	    return new BookmarkDTO(
    	         bookmark.getId(),
    	         bookmark.getTitle(),
    	         bookmark.getUrl(),
    	         bookmark.getCreatedAt()
	    );}
}
