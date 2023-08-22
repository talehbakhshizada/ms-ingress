package az.company.msingress.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {

    CARD_NOT_FOUND("Card not found with ID: %s "),

    UNEXPECTED_ERROR("Something went wrong "),

    BOOK_NOT_FOUND("Book not found with ID: %s "),

    COMMENT_NOT_FOUND("Comment not found with ID: %s"),

    POST_NOT_FOUND("Post not found with ID: %s");

    private final String message;
}
