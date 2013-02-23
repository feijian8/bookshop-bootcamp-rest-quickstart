package com.bookshop.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BookVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String title;
	private String author;
	private String releaseDate;
	private String categories;
	
	public BookVo() {
	}

	public BookVo(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.releaseDate = formatDate(book.getReleaseDate());
		this.categories = Arrays.toString(book.getCategories());
	}

	private static String formatDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
		return df.format(date);
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

	public String getCategories() {
		return categories;
	}

}
