package app.specification;

import app.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<User> {
    private final User filter;

    public UserSpecification(User filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate p = cb.disjunction();


        if (filter.getId() != null) {
            p.getExpressions()
                    .add(cb.equal(root.get("id"), filter.getId()));
        }

        if (filter.getName() != null) {
            p.getExpressions()
                    .add(cb.equal(root.get("name"), filter.getName()));
        }

        if (filter.getEmail() != null) {
            p.getExpressions()
                    .add(cb.equal(root.get("email"), filter.getEmail()));
        }

        if (filter.getName() != null && filter.getEmail() != null) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("name"), filter.getName()),
                            cb.equal(root.get("email"), filter.getEmail())));
        }

        return p;
    }
}
