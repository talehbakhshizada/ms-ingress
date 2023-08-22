package az.company.msingress.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponse {
    Long id;
    String pan;
    String cvv;
    String cardholder;
    LocalDate expireDate;
}
