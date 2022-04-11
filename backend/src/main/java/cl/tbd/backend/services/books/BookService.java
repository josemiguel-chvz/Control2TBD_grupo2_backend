package cl.tbd.backend.services.books;
import java.util.ArrayList;
import java.util.List;
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

    public BookModel update(Integer book_id, BookModel updated_book_data) {
        BookModel book = bookRepository.find(book_id);
        // libro existe
        if (book != null) {
            // Si algún  parametro viene como null, se deja el valor anterior
            if (updated_book_data.getSku() == null) {
                updated_book_data.setSku(book.getSku());
            }

            if (updated_book_data.getTitle() == null) {
                updated_book_data.setTitle(book.getTitle());
            }

            if (updated_book_data.getAuthor() == null) {
                updated_book_data.setAuthor(book.getAuthor());
            }
        
            if (updated_book_data.getPages() == null) {
                updated_book_data.setPages(book.getPages());
            }

            if (updated_book_data.getLanguage() == null) {
                updated_book_data.setLanguage(book.getLanguage());
            }

            Integer updated_book_id = bookRepository.update(book_id, updated_book_data);
            BookModel updated_book = bookRepository.find(updated_book_id);
            return updated_book;
            
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
