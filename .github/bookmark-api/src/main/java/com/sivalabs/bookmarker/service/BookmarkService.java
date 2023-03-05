package com.sivalabs.bookmarker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.bookmarker.dto.BookmarkDTO;
import com.sivalabs.bookmarker.dto.BookmarksDTO;
import com.sivalabs.bookmarker.entity.Bookmark;
import com.sivalabs.bookmarker.mapper.BookmarkMapper;
import com.sivalabs.bookmarker.repository.BookmarkRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
	
	private final BookmarkRepository bookmarkRepository;
	
	//private final BookmarkMapper bookmarkMapper;
	
	@Transactional(readOnly = true)
	public BookmarksDTO getBookmarks(Integer page){
		int pageNo = page < 1 ? 0 : page-1;
		Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
		Page<BookmarkDTO> bookmarkPage = 
				//bookmarkRepository.findAll(pageable)
							//.map(bookmark -> bookmarkMapper.toDTO(bookmark));
				bookmarkRepository.findBookmarks(pageable);
		//now we do not need the mapper
		return new BookmarksDTO(bookmarkPage);	
	}
}
