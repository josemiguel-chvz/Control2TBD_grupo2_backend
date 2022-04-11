package cl.tbd.backend.repositories.books;

import java.util.List;
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
        final String query =  "INSERT INTO books(sku, title, author, pages, language, is_deleted, created_at) " +
                              "VALUES(:sku, :title, :author, :pages, :language, False, now());";

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
                              "language, created_at, updated_at, deleted_at, is_deleted "+
                              "FROM books "+
                              "WHERE id = :book_id "+
                              "AND is_deleted = False";

        try(Connection conn = sql2o.open()){
            return conn.createQuery(query)
                        .addParameter("book_id", book_id)
                        .executeAndFetchFirst(BookModel.class);
        }
    }

    @Override
    public Integer update(Integer book_id, BookModel updated_book_data) {
       final String query = "UPDATE books SET " +
                            "sku = :sku, title = :title, author = :author, " +
                            "pages = :pages, language = :language, " +
                            "updated_at = now() " +
                            "WHERE id = :book_id";
        
        try(Connection conn = sql2o.open()){
            return (int) conn.createQuery(query)
                        .addParameter("sku", updated_book_data.getSku())
                        .addParameter("title", updated_book_data.getTitle())
                        .addParameter("author", updated_book_data.getAuthor())
                        .addParameter("pages", updated_book_data.getPages())
                        .addParameter("language", updated_book_data.getLanguage())
                        .addParameter("book_id", book_id)
                        .executeUpdate()
                        .getKey();
        }
    }

@Override
public List<BookModel> findAll() {
        final String query = "SELECT id, sku, title, author, pages," +
                             "language, created_at, updated_at, deleted_at, is_deleted " +
                             "FROM books " +
                             "WHERE is_deleted = False";

        try(Connection conn = sql2o.open()) {
            return conn.createQuery(query)
                        .executeAndFetch(BookModel.class);
        }
    }
}
