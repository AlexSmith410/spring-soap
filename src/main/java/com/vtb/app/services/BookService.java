package com.vtb.app.services;

import com.vtb.app.bookstore.Book;
import com.vtb.app.exceptions.ResourceNotFoundException;
import com.vtb.app.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Книга с таким id отсутствует в базе данных"));
    }

    public Book saveOrUpdate(Book book) {
        return bookRepository.save(book);
    }

    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
