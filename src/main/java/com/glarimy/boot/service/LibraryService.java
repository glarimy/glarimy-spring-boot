package com.glarimy.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.glarimy.boot.api.Book;
import com.glarimy.boot.api.Library;
import com.glarimy.boot.data.LibraryDAO;

@Service
@EnableTransactionManagement
public class LibraryService implements Library {
	@Autowired
	private LibraryDAO dao;

	@Override

	@Transactional
	public void add(Book book) {
		dao.save(book);
	}

	@Override
	public Book read(int isbn) {
		return dao.findOne(isbn);
	}

	@Override
	public List<Book> read() {
		Iterable<Book> results = dao.findAll();
		List<Book> list = new ArrayList<Book>();
		results.forEach(list::add);
		return list;
	}
}