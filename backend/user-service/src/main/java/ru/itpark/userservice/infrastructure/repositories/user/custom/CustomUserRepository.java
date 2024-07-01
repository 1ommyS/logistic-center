package ru.itpark.userservice.infrastructure.repositories.user.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itpark.userservice.application.user.query.contracts.SearchParams;
import ru.itpark.userservice.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomUserRepository {
    private final EntityManager em;

    public List<User> search(SearchParams searchParams) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(User.class);

        var root = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();


        if (searchParams.getId() != null) {
            predicates.add(cb.equal(root.get("id"), searchParams.getId()));
        }

        if (searchParams.getLogin() != null) {
            predicates.add(cb.equal(root.get("login"), searchParams.getLogin()));
        }

        if (searchParams.getEmail() != null) {
            predicates.add(cb.equal(root.get("email"), searchParams.getEmail()));
        }

        if (searchParams.getLanguages() != null) {
            predicates.add(cb.equal(root.get("languages"), searchParams.getLanguages()));
        }

        if (searchParams.getDateInfo() != null && searchParams.getDateInfo().getCreatedAt() != null) {
            predicates.add(cb.equal(root.get("createdAt"), searchParams.getDateInfo().getCreatedAt()));
        }
        if (searchParams.getDateInfo() != null && searchParams.getDateInfo().getDeletedAt() != null) {
            predicates.add(cb.equal(root.get("deletedAt"), searchParams.getDateInfo().getDeletedAt()));
        }

        final Predicate[] a = new Predicate[0];

        query.where(cb.and(predicates.toArray(a)));

        var typedQuery = em.createQuery(query);

        if (searchParams.getLimit() != null && searchParams.getLimit() > 0) {
            typedQuery.setMaxResults(searchParams.getLimit());
        }

        if (searchParams.getOffset() != null && searchParams.getOffset() >= 0) {
            typedQuery.setFirstResult(searchParams.getOffset());
        }

        return typedQuery.getResultList();
    }
}
