package com.glarimy.boot.api;

import java.util.List;

public interface Library {
	public void add(Book book);
	public Book read(int isbn);
	public List<Book> read();
}
