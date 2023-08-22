package az.company.msingress.service;

import az.company.msingress.dao.entity.UserEntity;
import az.company.msingress.dao.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // Testing n+1;

//    @PostConstruct
//    public void getUsers(){
//        var users = userRepository.findByNameContaining("Taleh");
//        users.get(0).getArticles().forEach(article -> System.out.println(article.getId()));
//    }

//    @PostConstruct
//    public List<UserEntity> getAllUsers() {
//        return userRepository.findAll();
//    }
}
