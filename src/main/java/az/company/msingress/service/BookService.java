package az.company.msingress.service;

import az.company.msingress.dao.entity.BookEntity;
import az.company.msingress.dao.repository.BookRepository;
import az.company.msingress.mapper.BookMapper;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponse> getAllBooks() {
        log.info("ActionLog.getAllUsers.start");
        log.info("ActionLog.getAllUsers.end");
         return bookRepository.findAll()
                 .stream()
                 .map(BookMapper::mapBookToBookResponse)
                 .collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        log.info("ActionLog.getBookById.start id:{}",id);
        log.info("ActionLog.getBookById.end id:{}",id);
        var book = fetchBookIfExist(id);
        var book2 = bookRepository.findById(id).orElseGet(null);
        return mapBookToBookResponse(book);
    }

    public void createBook(CreateBookRequest bookRequest) {
        log.info("ActionLog.createBook.start");
        bookRepository.save(mapBookRequestToBook(bookRequest));
        log.info("ActionLog.createBook.end");
    }

    public void deleteBook(Long id){
        log.info("ActionLog.deleteBook.start id:{}",id);
        var book = fetchBookIfExist(id);
        book.setStatus(DELETED);
        bookRepository.save(book);
        log.info("ActionLog.deleteBook.end id:{}",id);
    }

    public void updateBook(Long id, UpdateBookRequest bookRequest){
        log.info("ActionLog.updateBook.start id:{}",id);
        var book = fetchBookIfExist(id);
        updateBookEntity(book, bookRequest);
        bookRepository.save(book);
        log.info("ActionLog.updateBook.end id:{}",id);
    }

    public void updateTitle(Long id, String title) {
        log.info("ActionLog.updateTitle.start id:{}",id);
        var book = fetchBookIfExist(id);
        book.setTitle(title);
        bookRepository.save(book);
        log.info("ActionLog.updateTitle.end id:{}",id);
    }

    private BookEntity fetchBookIfExist(Long id){
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("BOOK_NOT_FOUND")
        );
    }
}
