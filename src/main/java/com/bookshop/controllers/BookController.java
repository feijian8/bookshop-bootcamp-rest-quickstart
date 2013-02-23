package com.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookshop.domain.Book;
import com.bookshop.domain.BookVo;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BookVo> findAllBooks() {
		List<Book> allBooks = mongoTemplate.findAll(Book.class);
		return allBookVos(allBooks);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BookVo findBook(@PathVariable("id") String id) {
		Query query = Query.query(Criteria.where("_id").is(id));
		return new BookVo(mongoTemplate.findOne(query, Book.class));
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BookVo addBook(@RequestBody BookVo vo) {
		Book book = new Book(vo);
		mongoTemplate.save(book);
		return new BookVo(book);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable("id") String id){
		Query query = Query.query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, Book.class);
	}
	
	private List<BookVo> allBookVos(List<Book> allBooks) {
		List<BookVo> bookVos = new ArrayList<BookVo>();
		for (Book book : allBooks) {
			bookVos.add(new BookVo(book));
		}
		return bookVos;
	}
}
