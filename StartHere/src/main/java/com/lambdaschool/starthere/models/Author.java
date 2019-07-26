package com.lambdaschool.starthere.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;
    private String lastname;
    private String firstname;

    @ManyToMany
    @JoinTable(name = "authorBooks",
                joinColumns = {@JoinColumn(name = "authorid")},
                inverseJoinColumns = {@JoinColumn(name = "bookid")})
    @JsonIgnoreProperties("authors")
    private List<Book> bookList = new ArrayList<>();

    public Author()
    {
    }

    public Author(String lastname, String firstname, List<Book> bookList)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.bookList = bookList;
    }

    public long getAuthorid()
    {
        return authorid;
    }

    public void setAuthorid(long authorid)
    {
        this.authorid = authorid;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public List<Book> getBookList()
    {
        return bookList;
    }

    public void setAuthors(List<Book> bookList)
    {
        this.bookList = bookList;
    }
}
