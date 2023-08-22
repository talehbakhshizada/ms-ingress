package az.company.msingress.model.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String pin;
    private String address;

    @Override
    public String toString() {
        return "PersonDTO{" +
                "pin='" + pin + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
