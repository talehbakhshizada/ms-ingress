package az.company.msingress.service;

import az.company.msingress.dao.entity.CardEntity;
import az.company.msingress.dao.repository.CardRepository;
import az.company.msingress.enums.CardStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class Test2Service {

    private final CardRepository cardRepository;

    @Transactional
    public void test2() {
        cardRepository.save(new CardEntity(null, "4", LocalDate.now(), "s", "dsf",
                CardStatus.ACTIVE, null, null));
        test3();
    }

    public void test3() {
        throw new RuntimeException("STOPPED");
    }
}
