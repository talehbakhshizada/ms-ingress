package az.company.msingress.service;

import az.company.msingress.model.cache.PersonDTO;
import az.company.msingress.util.CacheUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final CacheUtil cacheUtil;

    //testing redis
    @PostConstruct
    public void test() {
        //  cacheUtil.saveToCache("test-1", new PersonDTO("123","Baku"),1L, ChronoUnit.MINUTES);

        //  var person = cacheUtil.getBucket("test-1");
        //  System.out.println(person);
    }
}
