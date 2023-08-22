package az.company.msingress.controller

import az.company.msingress.model.request.CreateBookRequest
import az.company.msingress.model.request.UpdateBookRequest
import az.company.msingress.model.response.BookResponse
import az.company.msingress.service.BookService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static az.company.msingress.enums.BookStatus.EXIST
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

class BookControllerTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private BookService bookService
    private MockMvc mockMvc


    def setup() {
        bookService = Mock()
        def bookController = new BookController(bookService)
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build()
    }

    def "TestGetAllBooks"() {
        given:
        def url = "/v1/books"
        def books = random.nextObject(List<BookResponse>)

        when:
        def result = mockMvc.perform(
                get(url)
        )
                .andReturn()

        then:
        1 * bookService.getAllBooks();

        def response = result.response
        response.status == HttpStatus.OK.value()
        response.getContentAsString() != null
        result.response.getContentAsString().size() == books.size()
    }

    def "TestGetAllBooks empty list"() {
        given:
        def url = "/v1/books"

        when:
        def result = mockMvc.perform(
                get(url)
        )
                .andReturn()

        then:
        1 * bookService.getAllBooks();

        def response = result.response
        response.status == HttpStatus.OK.value()
        result.response.getContentAsString().size() == 0
    }

    def "TestGetBookById"() {
        given:
        def id = 1L
        def url = "/v1/books/$id"

        def bookResponse = new BookResponse(1L, "title1", "author1", EXIST)

        def expectedJsonResponse = '''
           {
             "id": 1,
             "title": "title1",
             "author": "author1",
             "status": "EXIST"
           }
        '''

        when:
        def result = mockMvc.perform(
                get(url)
        )
                .andReturn()

        then:
        1 * bookService.getBookById(id) >> bookResponse

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedJsonResponse, response.getContentAsString(), false)
    }

    def "TestCreateBook"() {
        given:
        def url = "/v1/books"
        def request = new CreateBookRequest("title1", "author1")

        def requestBody = '''
               {
                  "title": "title1",
                  "author": "author1"
               }
        '''
        when:
        def result = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andReturn()

        then:
        1 * bookService.createBook(request)

        def response = result.response
        response.status == HttpStatus.CREATED.value()
    }

    def "TestUpdateBook"() {
        given:
        def id = random.nextLong()
        def url = "/v1/books/$id"
        def request = new UpdateBookRequest("title1", "author1")

        def requestBody = '''
               {
                  "title": "title1",
                  "author": "author1"
               }
        '''

        when:
        def result = mockMvc.perform(
                put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andReturn()

        then:
        1 * bookService.updateBook(id, request)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }

    def "TestUpdateTitle"() {
        given:
        def id = random.nextLong()
        def url = "/v1/books/$id"
        def title = "title1"

        def requestBody = '''
               {
                  "title": "title1",
               }
        '''

        when:
        def result = mockMvc.perform(
                patch(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .param("title", "title1")
        )
                .andReturn()

        then:
        1 * bookService.updateTitle(id,title)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }
}
