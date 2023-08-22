package az.company.msingress.service.specification;

import az.company.msingress.dao.entity.CardEntity;
import az.company.msingress.model.criteria.CardCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static az.company.msingress.model.constants.CriteriaConstants.CVV;
import static az.company.msingress.model.constants.CriteriaConstants.PAN;

@AllArgsConstructor
public class CardSpecification implements Specification<CardEntity> {

    private CardCriteria cardCriteria;

    @Override
    public Predicate toPredicate(Root<CardEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (cardCriteria != null) {
            if (cardCriteria.getCvvFrom() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get(CVV), cardCriteria.getCvvFrom()));
            }

            if (cardCriteria.getCvvTo() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get(CVV), cardCriteria.getCvvTo()));
            }

            if (StringUtils.hasText(cardCriteria.getPan())) {
                predicates.add(
                        cb.like(root.get(PAN), "%" + cardCriteria.getPan() + "%"));
            }
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }

//    Best practise
//    @Override
//    public Predicate toPredicate(Root<CardEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//        var predicates = PredicateUtil.builder()
//                .addNullSafety(cardCriteria.getPan(),
//                        pan ->  cb.like(root.get(PAN), applyLikePattern(pan))
//                )
//                .addNullSafety(cardCriteria.getCvvFrom(), cvvFrom -> cb.greaterThanOrEqualTo(root.get(CVV), cvvFrom))
//                .addNullSafety(cardCriteria.getCvvTo(), cvvTo -> cb.lessThanOrEqualTo(root.get(CVV), cvvTo))
//                .build();
//        return cb.and(predicates);
//    }
}
