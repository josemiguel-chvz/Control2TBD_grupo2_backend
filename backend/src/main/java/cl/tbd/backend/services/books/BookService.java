package cl.tbd.backend.services.books;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.tbd.backend.models.books.BookModel;
import cl.tbd.backend.repositories.books.BookRepository;

import cl.tbd.backend.exceptions.NotFoundException;


@Service
public class BookService {
    
    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel create(BookModel new_book){
        Integer new_book_id = bookRepository.create(new_book);
        BookModel book = bookRepository.find(new_book_id);

        return book;
    }

    public BookModel find(Integer book_id) {
        BookModel book = bookRepository.find(book_id);

        if (book != null) {
            return book;
        } else {
            throw new NotFoundException("El libro indicado no existe");
        }
    }
   
    public List<BookModel> findAll() {
        List<BookModel> dogs = new ArrayList<>();
        Iterable<BookModel> records = bookRepository.findAll();
        records.forEach(dogs::add);

        return dogs;
    }

}
