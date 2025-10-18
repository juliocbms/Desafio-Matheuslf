package com.julio.desafio.services.Specification;

import com.julio.desafio.entity.Project;
import com.julio.desafio.entity.Task;
import com.julio.desafio.enums.Priority;
import com.julio.desafio.enums.Status;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

    public static Specification<Task> getTasksByCriteria(Status status, Priority priority, Long projectId) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();
            if (status != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
            }
            if (priority != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("priority"), priority));
            }
            if (projectId != null) {
                Join<Task, Project> projectJoin = root.join("project");
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(projectJoin.get("id"), projectId));
            }

            return predicate;
        };
    }
}