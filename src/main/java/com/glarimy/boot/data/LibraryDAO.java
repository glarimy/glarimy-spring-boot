package com.glarimy.boot.data;

import org.springframework.data.repository.CrudRepository;

import com.glarimy.boot.api.Book;

public interface LibraryDAO extends CrudRepository<Book, Integer> {

}
