package com.sivalabs.bookmarker.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookmarks")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Bookmark {
	@Id
	@SequenceGenerator(name = "bm_id_seq_gen", sequenceName = "bm_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bm_id_seq_gen")
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String url;
	private Instant createdAt;
}
