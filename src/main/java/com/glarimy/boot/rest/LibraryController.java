package com.glarimy.boot.rest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glarimy.boot.api.Book;
import com.glarimy.boot.api.Library;

@Controller
@RequestMapping(path = "/library")
public class LibraryController {
	@Autowired
	private Library library;
	private static String FILE_PATH = "D:\\Glarimy\\Workspace\\3M\\glarimy-spring-boot\\src\\main\\resources\\";

	@RequestMapping(path = "/book", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Book> add(@RequestBody Book book) {
		library.add(book);
		return ResponseEntity.ok().body(book);
	}

	@RequestMapping(path = "/book/{isbn}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Book> find(@PathVariable("isbn") int isbn) {
		Book book = library.read(isbn);
		if (book == null)
			return ResponseEntity.status(404).build();
		return ResponseEntity.ok().body(book);
	}

	@RequestMapping(path = "/book", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Book>> list() {
		List<Book> list = library.read();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(path = "/image/{isbn}", method = RequestMethod.GET, produces = "image/png")
	@ResponseBody
	public ResponseEntity<InputStreamResource> fetch(@PathVariable("isbn") int isbn) {
		try {
			InputStream is = new FileInputStream(FILE_PATH + "scintillations.png");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(is));
		} catch (Exception e) {
			return ResponseEntity.status(404).build();
		}
	}
}