package co.istad.lms.base;


import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class BaseSpecification<T> {

    public Specification<T> filter(SpecsDto specsDto) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(specsDto.getColumn()), specsDto.getValue());
    }

    public Specification<T> filter(FilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (SpecsDto specs : filterDto.specsDto) {
                switch (specs.getOperation()) {

                    case EQUAL -> {

                        Predicate equal;
                        if (specs.getJoinTable() != null) {

                            Join<Object, Object> join = root.join(specs.getJoinTable());
                            if (Boolean.parseBoolean(specs.getValue())) {
                                Boolean value = Boolean.parseBoolean(specs.getValue());
                                equal = criteriaBuilder.equal(join.get(specs.getColumn()), value);
                            } else {
                                equal = criteriaBuilder.equal(join.get(specs.getColumn()), specs.getValue());
                            }
                        } else {
                            if (Boolean.parseBoolean(specs.getValue())) {
                                Boolean value = Boolean.parseBoolean(specs.getValue());
                                equal = criteriaBuilder.equal(root.get(specs.getColumn()), value);
                            } else {
                                equal = criteriaBuilder.equal(root.get(specs.getColumn()), specs.getValue());
                            }
                        }
                        predicates.add(equal);

                    }
                    case LIKE -> {
                        if (specs.getJoinTable() != null) {
                            Join<Object, Object> join = root.join(specs.getJoinTable());
                            Predicate like = criteriaBuilder.like(criteriaBuilder.lower(join.get(specs.getColumn())), "%" + specs.getValue().toLowerCase() + "%");
                            predicates.add(like);
                        } else {
                            Predicate like = criteriaBuilder.like(criteriaBuilder.lower(root.get(specs.getColumn())), "%" + specs.getValue().toLowerCase() + "%");
                            predicates.add(like);
                        }
                    }
                    case JOIN -> {
                        Join<Object, Object> join = root.join(specs.getJoinTable());
                        Predicate joinPredicate = criteriaBuilder.equal(join.get(specs.getColumn()), specs.getValue());
                        predicates.add(joinPredicate);
                    }

                    case IN -> {
                        String[] split = specs.getValue().split(",");
                        Predicate in = root.get(specs.getColumn()).in(Arrays.asList(split));
                        predicates.add(in);
                    }
                    case GREATER_THAN -> {
                        Predicate greaterThan = criteriaBuilder.greaterThan(root.get(specs.getColumn()), specs.getValue());
                        predicates.add(greaterThan);
                    }
                    case LESS_THAN -> {
                        Predicate lessThan = criteriaBuilder.lessThan(root.get(specs.getColumn()), specs.getValue());
                        predicates.add(lessThan);
                    }
                    case BETWEEN -> {
                        String[] split1 = specs.getValue().split(",");
                        Predicate between = criteriaBuilder.between(root.get(specs.getColumn()), Long.parseLong(split1[0]),
                                Long.parseLong(split1[1]));
                        predicates.add(between);
                    }
                    default -> throw new IllegalStateException("Unexpected value: ");
                }

            }
            if (filterDto.globalOperator.equals(FilterDto.GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }

    @Getter
    @Setter
    public static class SpecsDto {
        private String column;
        private String value;
        private String joinTable;

        private Operation operation;

        public enum Operation {
            EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN;
        }
    }

    @Getter
    @Setter
    public static class FilterDto {

        private List<SpecsDto> specsDto;
        private GlobalOperator globalOperator;

        public enum GlobalOperator {
            AND, OR;
        }

    }
}
