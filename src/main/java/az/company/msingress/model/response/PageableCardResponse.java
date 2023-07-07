package az.company.msingress.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableCardResponse {
    private List<CardResponse> cards;
    private long totalElements;
    private int totalPages;
    private boolean hasNextPage;
}
