package az.company.msingress.service;

import az.company.msingress.dao.entity.CardEntity;
import az.company.msingress.dao.repository.CardRepository;
import az.company.msingress.mapper.CardMapper;
import az.company.msingress.model.criteria.CardCriteria;
import az.company.msingress.model.criteria.PageCriteria;
import az.company.msingress.model.request.CreateCardRequest;
import az.company.msingress.model.request.UpdateCardRequest;
import az.company.msingress.model.response.CardResponse;
import az.company.msingress.model.response.PageableCardResponse;
import az.company.msingress.service.specification.CardSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static az.company.msingress.enums.CardStatus.BLOCKED;
import static az.company.msingress.mapper.CardMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {

    private final CardRepository cardRepository;

    public CardResponse getCardById(Long id) {
        var card = fetchCardIfExist(id);
        return buildCardResponse(card);
    }

    public PageableCardResponse getCards(PageCriteria pageCriteria, CardCriteria cardCriteria) {
        var pageableCards = cardRepository.findAll(new CardSpecification(cardCriteria),
        PageRequest.of(pageCriteria.getPage(),pageCriteria.getCount()));

        var cards = pageableCards.getContent()
                .stream()
                .map(CardMapper::buildCardResponse)
                .collect(Collectors.toList());

        return PageableCardResponse.builder()
                .cards(cards)
                .totalPages(pageableCards.getTotalPages())
                .totalElements(pageableCards.getTotalElements())
                .hasNextPage(pageableCards.hasNext())
                .build();
    }

    public void saveCard(CreateCardRequest request) {
        cardRepository.save(buildCardEntity(request));
    }

    public void updateCard(UpdateCardRequest request, Long id) {
        var card = fetchCardIfExist(id);
        updateCardEntity(card, request);
        cardRepository.save(card);
    }

    public void deleteCard(Long id){
        var card = fetchCardIfExist(id);
        card.setStatus(BLOCKED);
        cardRepository.save(card);
    }

    public void updatePan(Long id,String pan){
        var card = fetchCardIfExist(id);
        updateCardPan(card, pan);
        cardRepository.save(card);
    }

    private CardEntity fetchCardIfExist(Long id) {
        return cardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("CARD_NOT_FOUND")
        );
    }

}
