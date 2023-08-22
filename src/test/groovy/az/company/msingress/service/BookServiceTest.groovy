package az.company.msingress.service

import az.company.msingress.dao.entity.BookEntity
import az.company.msingress.dao.repository.BookRepository
import az.company.msingress.enums.BookStatus
import az.company.msingress.model.request.CreateBookRequest
import az.company.msingress.model.request.UpdateBookRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification


import static az.company.msingress.enums.BookStatus.EXIST
import static az.company.msingress.mapper.BookMapper.*

class BookServiceTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private BookRepository bookRepository
    private BookService bookService;

    def setup() {
        bookRepository = Mock()
        bookService = new BookService(bookRepository)
    }

    def "TestGetBookById success"() {
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(BookEntity)

        when:
        def actual = bookService.getBookById(id)

        then:
        1 * bookRepository.findById(id) >> Optional.of(entity)
        actual.id == entity.id
        actual.title == entity.title
        actual.author == entity.author
        actual.status == EXIST
    }

    def "TestGetBookById invalid id"() {
        given:
        def id = random.nextObject(Long)

        when:
        bookService.getBookById(id)

        then:
        1 * bookRepository.findById(id) >> Optional.empty()

        RuntimeException ex = thrown()
        ex.message == "BOOK_NOT_FOUND"
    }

    def "TestGetAllBooks"() {
        given:
        def books = random.nextObject(List<BookEntity>)

        when:
        def actual = bookService.getAllBooks()

        then:
        1 * bookRepository.findAll() >> books
        actual!=null
        actual.size()==books.size()
    }

    def "TestCreateBook"() {
        given:
        def bookRequest = random.nextObject(CreateBookRequest)

        when:
        bookService.createBook(bookRequest)

        then:
        1 * bookRepository.save(mapBookRequestToBook(bookRequest))
    }

    def "TestDeleteBook success"() {
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(BookEntity)

        when:
        bookService.deleteBook(id)

        then:
        1 * bookRepository.findById(id) >> Optional.of(entity)
        1 * bookRepository.save(entity)
        entity.status == BookStatus.DELETED
    }

    def "TestDeleteBook invalid id"() {
        given:
        def id = random.nextObject(Long)

        when:
        bookService.deleteBook(id)

        then:
        1 * bookRepository.findById(id) >> Optional.empty()
        0 * bookRepository.save()

        RuntimeException ex = thrown()
        ex.message == "BOOK_NOT_FOUND"
    }

    def "TestUpdateBook success"() {
        given:
        def id = random.nextObject(Long)
        def updateRequest = random.nextObject(UpdateBookRequest)
        def entity = random.nextObject(BookEntity)

        when:
        bookService.updateBook(id,updateRequest)

        then:
        1 * bookRepository.findById(id) >> Optional.of(entity)
        entity.title==updateRequest.title
        entity.author==updateRequest.author
        1 * bookRepository.save(entity)
    }

    def "TestUpdateBook invalid id"() {
        given:
        def id = random.nextObject(Long)
        def updateRequest = random.nextObject(UpdateBookRequest)

        when:
        bookService.updateBook(id,updateRequest)

        then:
        1 * bookRepository.findById(id) >> Optional.empty()
        0 * bookRepository.save()

        RuntimeException ex = thrown()
        ex.message == "BOOK_NOT_FOUND"
    }

    def "TestUpdateTitle success"() {
        given:
        def id = random.nextObject(Long)
        def title = random.nextObject(String)
        def entity = random.nextObject(BookEntity)

        when:
        bookService.updateTitle(id,title)

        then:
        1 * bookRepository.findById(id) >> Optional.of(entity)
        entity.title==title
        1 * bookRepository.save(entity)
    }

    def "TestUpdateTitle invalid id"() {
        given:
        def id = random.nextObject(Long)
        def title = random.nextObject(String)

        when:
        bookService.updateTitle(id,title)

        then:
        1 * bookRepository.findById(id) >> Optional.empty()
        0 * bookRepository.save()

        RuntimeException ex = thrown()
        ex.message == "BOOK_NOT_FOUND"
    }
}
