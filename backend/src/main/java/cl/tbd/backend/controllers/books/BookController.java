package cl.tbd.backend.controllers.books;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import cl.tbd.backend.models.books.BookModel;
import cl.tbd.backend.services.books.BookService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class BookController {
    
    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Create Book
    @PostMapping("/books")
    public ResponseEntity<BookModel> create(@RequestBody @Valid BookModel new_book) {
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
        return ResponseEntity.ok().body(book);
    }

    // Update Book
    @PutMapping("/books/{id}")
    public ResponseEntity<BookModel> update(@PathVariable("id") Integer book_id, @RequestBody BookModel updated_book) {
        BookModel book = bookService.update(book_id, updated_book);
        return ResponseEntity.ok().body(book);
    }

    // Delete Book
    @DeleteMapping("/books/{id}")
    public ResponseEntity<BookModel> deleteTEntity(@PathVariable("id") Integer book_id) {
        bookService.delete(book_id);
        return ResponseEntity.noContent().build();
        
      }
    
    // Find All Books
    @GetMapping("/books")
    public ResponseEntity<List<BookModel>> findAll() {
        List<BookModel> books = bookService.findAll();
        
        return ResponseEntity.ok().body(books); 
    } 

   
    // Validation Exceptions
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}



