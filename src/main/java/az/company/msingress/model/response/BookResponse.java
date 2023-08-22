package az.company.msingress.model.response;

import az.company.msingress.enums.BookStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    Long id;
    String title;
    String author;
    BookStatus status;
}
