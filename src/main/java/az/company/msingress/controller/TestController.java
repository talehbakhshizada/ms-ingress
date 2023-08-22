package az.company.msingress.controller;

import az.company.msingress.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class TestController {

    private final TestService testService;

    @GetMapping
    public void test(){
        testService.test();
    }
}
