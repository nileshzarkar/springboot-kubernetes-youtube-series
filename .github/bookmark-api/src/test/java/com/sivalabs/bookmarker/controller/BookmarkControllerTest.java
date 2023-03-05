package com.sivalabs.bookmarker.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.github.javafaker.Faker;
import com.sivalabs.bookmarker.entity.Bookmark;
import com.sivalabs.bookmarker.repository.BookmarkRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:tc:postgresql:14-alpine://dummy"
})
public class BookmarkControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	// This is used to fire an API call.
	
	@Autowired
	BookmarkRepository bookmarkRepository;
	private List<Bookmark> bookmarks;
	@BeforeEach
	void setUp() {
		bookmarkRepository.deleteAllInBatch();
		bookmarks = new ArrayList<>();
		Faker faker = new Faker();
		bookmarks.add(new Bookmark(null,faker.company().name(),faker.company().url(), Instant.now()));
		bookmarks.add(new Bookmark(null,faker.company().name(),faker.company().url(), Instant.now()));
		bookmarks.add(new Bookmark(null,faker.company().name(),faker.company().url(), Instant.now()));
		bookmarkRepository.saveAll(bookmarks);
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,3,1,1,false,false,true,true",
		"2,3,1,2,false,true,false,true"
	})
	void shouldGetBookMarks(int pageNumber,
			                int totalElements,
			                int totalPages, 
			                int currentPage,
			                boolean hasNext,
			                boolean hasPrevious,
			                boolean isFirst,
			                boolean isLast) throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/bookmarks?page="+pageNumber))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.isLast", CoreMatchers.equalTo(isLast)));
	}
}
