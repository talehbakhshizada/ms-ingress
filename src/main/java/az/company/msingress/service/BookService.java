package az.company.msingress.service;

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
        return bookRepository.findById(id)
              .map(BookMapper::mapBookToBookResponse)
              .orElseThrow(RuntimeException::new);
    }

    public void createBook(CreateBookRequest bookRequest) {
        log.info("ActionLog.createBook.start");
        bookRepository.save(BookMapper.mapBookRequestToBook(bookRequest));
        log.info("ActionLog.createBook.end");
    }

    public void deleteBook(Long id){
        log.info("ActionLog.deleteBook.start id:{}",id);
        bookRepository.deleteById(id);
        log.info("ActionLog.deleteBook.end id:{}",id);
    }

    public void updateBook(Long id, UpdateBookRequest bookRequest){
        log.info("ActionLog.updateBook.start id:{}",id);
        var book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        bookRepository.save(book);
        log.info("ActionLog.updateBook.end id:{}",id);
    }

    public void updateTitle(Long id, String title) {
        log.info("ActionLog.updateTitle.start id:{}",id);
        var book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        book.setTitle(title);
        bookRepository.save(book);
        log.info("ActionLog.updateTitle.end id:{}",id);
    }
}
