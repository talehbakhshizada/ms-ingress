package az.company.msingress.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AsyncService {

    @SneakyThrows
    @Async
    public void saveUser() {
        Thread.sleep(3000);
        //additional logic
        System.out.println("Saved user");
    }
}
