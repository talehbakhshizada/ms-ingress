package az.company.msingress.controller;

import az.company.msingress.model.criteria.CardCriteria;
import az.company.msingress.model.criteria.PageCriteria;
import az.company.msingress.model.request.CreateCardRequest;
import az.company.msingress.model.request.UpdateCardRequest;
import az.company.msingress.model.response.CardResponse;
import az.company.msingress.model.response.PageableCardResponse;
import az.company.msingress.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cards")
public class CardController {

    private final CardService cardService;

    @GetMapping("/{id}")
    public CardResponse getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @GetMapping
    public PageableCardResponse getCards(PageCriteria pageCriteria, CardCriteria cardCriteria) {
        return cardService.getCards(pageCriteria,cardCriteria);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveCard(@RequestBody CreateCardRequest request) {
        cardService.saveCard(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateCard(@PathVariable Long id, @RequestBody UpdateCardRequest card) {
        cardService.updateCard(card, id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateCardPan(@PathVariable Long id, @RequestParam String pan){
        cardService.updatePan(id, pan);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
