package az.company.msingress.controller

import az.company.msingress.model.request.CreateCardRequest
import az.company.msingress.model.response.CardResponse
import az.company.msingress.service.CardService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class CardControllerTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private CardService cardService
    private MockMvc mockMvc


    def setup() {
        cardService = Mock()
        def cardController = new CardController(cardService)
        mockMvc = MockMvcBuilders.standaloneSetup(cardController).build()
    }

    def "TestGetBookById"() {
        given:
        def id = 1L
        def url = "/v1/cards/$id"

        def cardResponse = new CardResponse(1L, "12345", "123", "Test", LocalDate.of(2023, 7, 1))

        def expectedJsonResponse = '''
               {
                  "id": 1,
                  "pan": "12345",
                  "cvv": "123",
                  "cardholder": "Test",
                  "expireDate": [2023,07,01]
               }
        '''

        when:
        def result = mockMvc.perform(
                get(url)
        )
                .andReturn()

        then:
        1 * cardService.getCardById(id) >> cardResponse

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedJsonResponse, response.getContentAsString(), false)
    }

//    def "TestSaveCard"() { // ???????????????
//        given:
//        def url = "/v1/cards"
//        def request = new CreateCardRequest("12345", LocalDate.of(2023, 7, 1), "123", "Test")
//
//        def requestBody = '''
//               {
//                  "pan": "12345",
//                  "expireDate": [2023,07,01]
//                  "cvv": "123",
//                  "cardholder": "Test"
//               }
//        '''
//        when:
//        def result = mockMvc.perform(
//                post(url)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody)
//        )
//                .andReturn()
//
//        then:
//        1 * cardService.saveCard(request)
//
//        def response = result.response
//        response.status == HttpStatus.CREATED.value()
//    }
}
