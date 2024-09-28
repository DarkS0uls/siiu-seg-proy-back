package com.myorg.adapter.out.postgres.users;

import com.myorg.adapter.out.postgres.users.entity.UsersEntity;
import com.myorg.adapter.out.postgres.users.entity.UsersStatusEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public class UsersAdapterSpecification implements Specification<UsersEntity> {

    private String status;   //query by enum value
    private String userName;  //query by like value
    private String cellphone; //query by exactly value
    private LocalDateTime createdDt; //query by date

    @Override
    public Predicate toPredicate(Root<UsersEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (status != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), UsersStatusEnum.fromString(status)));
        }
        if (userName != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%".concat(userName.trim()).concat("%")));
        }
        if (cellphone != null) {
            predicates.add(criteriaBuilder.equal(root.get("cellphone"), cellphone));
        }
        if (createdDt != null) {
            predicates.add(criteriaBuilder.equal(root.get("createdDt"), createdDt));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
