package az.company.msingress.mapper

import az.company.msingress.dao.entity.BookEntity
import az.company.msingress.model.request.CreateBookRequest
import az.company.msingress.model.request.UpdateBookRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification


import static az.company.msingress.mapper.BookMapper.mapBookRequestToBook
import static az.company.msingress.mapper.BookMapper.mapBookToBookResponse
import static az.company.msingress.mapper.BookMapper.updateBookEntity

class BookMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestMapBookToBookResponse"() {
        given:
        def book = random.nextObject(BookEntity)

        when:
        def actual = mapBookToBookResponse(book)

        then:
        actual.getId() == book.getId()
        actual.getTitle() == book.getTitle()
        actual.getAuthor() == book.getAuthor()
        actual.getStatus() == book.getStatus()
    }

    def "TestMapBookRequestToBook"() {
        given:
        def request = random.nextObject(CreateBookRequest)

        when:
        def actual = mapBookRequestToBook(request)

        then:
        actual.getTitle() == request.getTitle()
        actual.getAuthor() == request.getAuthor()
    }

    def "TestUpdateBookEntity"() {
        given:
        def request = random.nextObject(UpdateBookRequest)
        def entity = random.nextObject(BookEntity)

        when:
        updateBookEntity(entity,request)

        then:
        entity.getTitle()==request.getTitle()
        entity.getAuthor()==request.getAuthor()
    }


}
