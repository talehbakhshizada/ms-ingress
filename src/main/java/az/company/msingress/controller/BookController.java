package az.company.msingress.controller;

import az.company.msingress.model.request.CreateBookRequest;
import az.company.msingress.model.request.UpdateBookRequest;
import az.company.msingress.model.response.BookResponse;
import az.company.msingress.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody CreateBookRequest request) {
        bookService.createBook(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest request) {
        bookService.updateBook(id, request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateTitle(@PathVariable Long id, @RequestParam String title){
        bookService.updateTitle(id, title);
    }

}
