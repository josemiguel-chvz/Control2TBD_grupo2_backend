package cl.tbd.backend.controllers.books;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cl.tbd.backend.models.books.BookModel;
import cl.tbd.backend.services.books.BookService;

@RequestMapping("/api")
@RestController
public class BookController {
    
    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Create Book
    @PostMapping("/books")
    public ResponseEntity<BookModel> create(@RequestBody BookModel new_book) {
        BookModel book = bookService.create(new_book);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(book.getId())
                            .toUri();
        
        return ResponseEntity.created(location).body(book);
    }

    // Find Book
    @GetMapping("/books/{id}")
    public ResponseEntity<BookModel> find(@PathVariable("id") Integer book_id){
        BookModel book = bookService.find(book_id);
        // Revisar si existe, lanzar error cuando no exista
        return ResponseEntity.ok().body(book);
    }

    // Find All Books
    @GetMapping("/books")
    public ResponseEntity<List<BookModel>> findAll() {
        List<BookModel> books = bookService.findAll();
        
        return ResponseEntity.ok().body(books); 
    } 
}
