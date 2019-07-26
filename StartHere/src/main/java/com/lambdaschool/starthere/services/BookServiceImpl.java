package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public List<Book> findAll(Pageable pageable) {
        List<Book> bookList = new ArrayList<>();
        bookRepo.findAll(pageable).iterator().forEachRemaining(bookList::add);
        return bookList;
    }

    @Transactional
    @Override
    public Book updateBook(Book book, long id) {
        Book currentBook = bookRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        if(book.getBookTitle() != null){
            currentBook.setBookTitle(book.getBookTitle());
        }
        if(book.getCopyDate() != 0){
            currentBook.setCopyDate(book.getCopyDate());
        }
        if (book.getISBN() != null){
            currentBook.setISBN(book.getISBN());
        }
        if (book.getAuthorList() != null && book.getAuthorList().size() > 0){
            currentBook.setAuthorList(book.getAuthorList());
        }

        bookRepo.save(currentBook);
        return currentBook;
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (bookRepo.findById(id).isPresent()){
            bookRepo.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }

    }

    @Transactional
    @Override
    public void assignAuthor(long bookid, long authorid) {
        Book currentBook = bookRepo.findById(bookid).orElseThrow(EntityNotFoundException::new);
        currentBook.getAuthorList().add(authorRepo.findById(authorid).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void save(Book book) {
        bookRepo.save(book);
    }
}
