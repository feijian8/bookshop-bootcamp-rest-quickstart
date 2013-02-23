package com.bookshop.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {

	@Id
	private String id;
	
	private String title;
	private String author;
	private Date releaseDate;
	private String[] categories;

	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(BookVo vo) {
		this.title = vo.getTitle();
		this.author = vo.getAuthor();
		this.releaseDate = toDate(vo.getReleaseDate());
		this.categories = toStringArray(vo.getCategories());
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String[] getCategories() {
		return categories;
	}
	
	private static Date toDate(String date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String[] toStringArray(String input){
		return input.split(",");
	}
	
}
