package az.company.msingress.mapper;

import az.company.msingress.dao.entity.Book;
import az.company.msingress.model.request.CreateBookRequest;
import az.company.msingress.model.response.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public static BookResponse mapBookToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor()).
                build();
    }

    public static Book mapBookRequestToBook(CreateBookRequest request){
        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .build();
    }
}
