package az.company.msingress.controller;

import az.company.msingress.model.request.AccountRequest;
import az.company.msingress.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/validate")
    public void saveUser(@RequestBody AccountRequest accountRequest){
        accountService.validateAccount(accountRequest);
    }
}
