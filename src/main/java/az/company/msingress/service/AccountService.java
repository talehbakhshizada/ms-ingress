package az.company.msingress.service;

import az.company.msingress.model.request.AccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.company.msingress.model.constants.CurrencyConstants.eligibleCurrencies;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AsyncService asyncService;

    public void validateAccount(AccountRequest account) {
        if (!eligibleCurrencies.contains(account.getCurrency())) {
            throw new RuntimeException("NOT_ELIGIBLE_CURRENCY");
        }

        asyncService.saveUser();
    }
}
