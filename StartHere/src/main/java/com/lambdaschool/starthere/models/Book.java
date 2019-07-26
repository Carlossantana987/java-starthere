package com.lambdaschool.starthere.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @Column (nullable = false)
    private String bookTitle;
    private String ISBN;
    private int copyDate;

    @ManyToMany
    @JsonIgnoreProperties(value = "bookList")
    @JoinTable(name = "onFile" , joinColumns = {@JoinColumn(name = "bookid")},
    inverseJoinColumns = {@JoinColumn(name = "authorid")})
    List<Author> authorList = new ArrayList<>();

    Book()
    {
    }

    public Book(String bookTitle, String ISBN, int copyDate, List<Author> authorList)
    {
        this.bookTitle = bookTitle;
        this.ISBN = ISBN;
        this.copyDate = copyDate;
        this.authorList = authorList;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBookTitle()
    {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle)
    {
        this.bookTitle = bookTitle;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public int getCopyDate()
    {
        return copyDate;
    }

    public void setCopyDate(int copyDate)
    {
        this.copyDate = copyDate;
    }

    public List<Author> getAuthorList()
    {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList)
    {
        this.authorList = authorList;
    }
}
