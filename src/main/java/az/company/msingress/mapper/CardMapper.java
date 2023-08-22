package az.company.msingress.mapper;

import az.company.msingress.dao.entity.CardEntity;
import az.company.msingress.enums.CardStatus;
import az.company.msingress.model.request.CreateCardRequest;
import az.company.msingress.model.request.UpdateCardRequest;
import az.company.msingress.model.response.CardResponse;

import static az.company.msingress.enums.CardStatus.ACTIVE;

public class CardMapper {
    public static CardResponse buildCardResponse(CardEntity cardEntity) {
        return CardResponse.builder()
                .id(cardEntity.getId())
                .pan(cardEntity.getPan())
                .cvv(cardEntity.getCvv())
                .cardholder(cardEntity.getCardholder())
                .expireDate(cardEntity.getExpireDate())
                .build();
    }
    public static CardEntity buildCardEntity(CreateCardRequest request){
        return CardEntity.builder()
                .pan(request.getPan())
                .expireDate(request.getExpireDate())
                .cvv(request.getCvv())
                .cardholder(request.getCardholder())
                .status(ACTIVE)
                .build();
    }

    public static void updateCardEntity(CardEntity card,UpdateCardRequest request){
        card.setPan(request.getPan());
        card.setExpireDate(request.getExpireDate());
        card.setCvv(request.getCvv());
    }

    public static void updateCardPan(CardEntity card,String pan){
        card.setPan(pan);
    }
}
