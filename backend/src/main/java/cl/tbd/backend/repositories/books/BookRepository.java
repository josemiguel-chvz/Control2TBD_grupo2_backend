package cl.tbd.backend.repositories.books;
import java.util.List;

import cl.tbd.backend.models.books.BookModel;

public interface BookRepository {
    public Integer create(BookModel new_book);
    public BookModel find(Integer book_id);
    public List<BookModel> findAll();
}
