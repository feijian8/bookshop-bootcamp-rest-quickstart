package com.bookshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {

	@Id
	private String id;
	
	private String title;
	private String author;
	private String releaseDate;
	private String[] categories;

	public Book(String title, String author, String releaseDate,
			String[] categories) {
		super();
		this.title = title;
		this.author = author;
		this.releaseDate = releaseDate;
		this.categories = categories;
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String[] getCategories() {
		return categories;
	}

}
