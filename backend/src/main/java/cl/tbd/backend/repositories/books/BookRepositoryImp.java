package cl.tbd.backend.repositories.books;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.backend.models.books.BookModel;

@Repository
public class BookRepositoryImp implements BookRepository {
    
    @Autowired
    private Sql2o sql2o;

    @Override
    public Integer create(BookModel new_book) {
        final String query =  "INSERT INTO books(sku, title, author, pages, language, created_at) " +
                              "VALUES(:sku, :title, :author, :pages, :language, now());";

        // Chequear si sku ya existe?
        try(Connection conn = sql2o.open()){
            return (int) conn.createQuery(query)
                                    .addParameter("sku", new_book.getSku())
                                    .addParameter("title", new_book.getTitle())
                                    .addParameter("author", new_book.getAuthor())
                                    .addParameter("pages", new_book.getPages())
                                    .addParameter("language", new_book.getLanguage())
                                    .executeUpdate()
                                    .getKey();
        }     
    }


    @Override
    public BookModel find(Integer book_id) {
        final String query =  "SELECT id, sku, title, author, pages," +
                              "language, created_at, updated_at, deleted_at "+
                              "FROM books "+
                              "WHERE id = :book_id";

        try(Connection conn = sql2o.open()){
            return conn.createQuery(query)
                        .addParameter("book_id", book_id)
                        .executeAndFetchFirst(BookModel.class);
        }
    }

    @Override
    public List<BookModel> findAll() {
        final String query = "SELECT id, sku, title, author, pages," +
                             "language, created_at, updated_at, deleted_at " +
                             "FROM books";

        try(Connection conn = sql2o.open()) {
            return conn.createQuery(query)
                        .executeAndFetch(BookModel.class);
        }
    }
}
