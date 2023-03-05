package com.sivalabs.bookmarker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sivalabs.bookmarker.dto.BookmarksDTO;
import com.sivalabs.bookmarker.entity.Bookmark;
import com.sivalabs.bookmarker.service.BookmarkService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
	
	private final BookmarkService bookmarkService;
	
	@GetMapping
	public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page){
		return bookmarkService.getBookmarks(page);
	}
}
