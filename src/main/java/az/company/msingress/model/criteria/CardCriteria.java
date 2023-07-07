package az.company.msingress.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCriteria {
    private Integer cvvFrom;
    private Integer cvvTo;
    private String pan;
}
