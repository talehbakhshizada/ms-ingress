package az.company.msingress.mapper;

import az.company.msingress.dao.entity.BookEntity;
import az.company.msingress.model.request.CreateBookRequest;
import az.company.msingress.model.request.UpdateBookRequest;
import az.company.msingress.model.request.UpdateTitleRequest;
import az.company.msingress.model.response.BookResponse;

import static az.company.msingress.enums.BookStatus.EXIST;

public class BookMapper {
    public static BookResponse mapBookToBookResponse(BookEntity book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .status(EXIST)
                .build();
    }

    public static BookEntity mapBookRequestToBook(CreateBookRequest request) {
        return BookEntity.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .status(EXIST)
                .build();
    }

    public static void updateBookEntity(BookEntity book, UpdateBookRequest request){
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
    }

}
