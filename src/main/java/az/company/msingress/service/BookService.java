package az.company.msingress.service;

import az.company.msingress.dao.entity.BookEntity;
import az.company.msingress.dao.repository.BookRepository;
import az.company.msingress.exception.NotFoundException;
import az.company.msingress.mapper.BookMapper;
import az.company.msingress.model.constants.ErrorMessages;
import az.company.msingress.model.request.CreateBookRequest;
import az.company.msingress.model.request.UpdateBookRequest;
import az.company.msingress.model.response.BookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.company.msingress.enums.BookStatus.DELETED;
import static az.company.msingress.mapper.BookMapper.*;
import static az.company.msingress.model.constants.ErrorMessages.BOOK_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::mapBookToBookResponse)
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        var book = fetchBookIfExist(id);
        return mapBookToBookResponse(book);
    }

    public void createBook(CreateBookRequest bookRequest) {
        bookRepository.save(mapBookRequestToBook(bookRequest));
    }

    public void deleteBook(Long id) {
        var book = fetchBookIfExist(id);
        book.setStatus(DELETED);
        bookRepository.save(book);
    }

    public void updateBook(Long id, UpdateBookRequest bookRequest) {
        var book = fetchBookIfExist(id);
        updateBookEntity(book, bookRequest);
        bookRepository.save(book);
    }

    public void updateTitle(Long id, String title) {
        var book = fetchBookIfExist(id);
        book.setTitle(title);
        bookRepository.save(book);
    }

    private BookEntity fetchBookIfExist(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.valueOf(BOOK_NOT_FOUND),
                        String.format(BOOK_NOT_FOUND.getMessage(), id))
        );
    }
}
