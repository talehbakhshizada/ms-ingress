package az.company.msingress.service;

import az.company.msingress.dao.entity.CardEntity;
import az.company.msingress.dao.repository.BookRepository;
import az.company.msingress.dao.repository.CardRepository;
import az.company.msingress.enums.CardStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TestService {

    private final CardRepository cardRepository;
    private final Test2Service test2Service;

    // @Transactional
    public void test() {
        //test2();     doesnt rollback
        test2Service.test2();
    }

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
