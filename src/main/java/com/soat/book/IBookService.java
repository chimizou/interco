package com.soat.book;

import java.util.List;

public interface IBookService {

    Book saveBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Integer bookId);

    List<Book> findBooksByTitleOrPartTitle(String title);

    Book findBookByIsbn(String isbn);

    boolean checkIfIdExists(Integer id);

    List<Book> getBooksByCategory(String codeCategory);

}
