package alexander.ivanov.polls.frontend.util;

import alexander.ivanov.polls.frontend.model.Poll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PollSpecificationUtils {
    private static final Logger logger = LoggerFactory.getLogger(PollSpecificationUtils.class);

    public static Specification<Poll> getSpecificationFromParams(Map<String, String> params) {
        Specification<Poll> pollSpecification = empty()
                .and(new NameSpecification(params).like())
                .and(new StartDateSpecification(params).getSpecification())
                .and(new EndDateSpecification(params).getSpecification())
                .and(new ActivitySpecification(params).getSpecification());

        return pollSpecification;
    }

    public static Specification<Poll> empty() {
        return new Specification<Poll>() {
            @Override
            public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                logger.info("Objects.isNull(predicate) = {}", Objects.isNull(query.getRestriction()));
                return null;
            }
        };
    }

    public static class NameSpecification implements StringSpecification {
        public static final String FIELD_NAME = "name";
        public static final String NAME_LIKE = paramFormat(FIELD_NAME, SupportedOperations.LIKE);
        private Map<String, String> params = new HashMap<>();

        private NameSpecification() {
        }

        public NameSpecification(Map<String, String> params) {
            this.params = params;
        }

        @Override
        public Specification<Poll> getSpecification(Map<String, String> params, Specification<Poll> specification) {
            if (params.containsKey(NAME_LIKE)) {
                String value = params.get(NAME_LIKE);
                specification.and(like(value));
            }
            return specification;
        }

        @Override
        public Specification<Poll> like(String value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.like(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        @Override
        public Specification<Poll> like() {
            String value = null;
            if (params.containsKey(NAME_LIKE)) {
                value = params.get(NAME_LIKE);
            }
            return like(value);
        }
    }

    public static class StartDateSpecification implements DateSpecification {
        public static final String FIELD_NAME = "startDate";
        public static final String START_DATE_EQ = paramFormat(FIELD_NAME, SupportedOperations.EQ);
        public static final String START_DATE_GT = paramFormat(FIELD_NAME, SupportedOperations.GT);
        public static final String START_DATE_GTE = paramFormat(FIELD_NAME, SupportedOperations.GTE);
        public static final String START_DATE_LT = paramFormat(FIELD_NAME, SupportedOperations.LT);
        public static final String START_DATE_LTE = paramFormat(FIELD_NAME, SupportedOperations.LTE);
        private Map<String, String> params = new HashMap<>();

        private StartDateSpecification() {
        }

        public StartDateSpecification(Map<String, String> params) {
            this.params = params;
        }

        @Override
        public Specification<Poll> getSpecification() {
            return getSpecificationByParam();
        }

        @Override
        public Specification<Poll> eq(Date value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.equal(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        @Override
        public Specification<Poll> gt(Date value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.greaterThan(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        @Override
        public Specification<Poll> gte(Date value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.greaterThanOrEqualTo(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        @Override
        public Specification<Poll> lt(Date value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.lessThan(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        @Override
        public Specification<Poll> lte(Date value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.lessThan(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        private Specification<Poll> getSpecificationByParam() {
            Date value = null;
            Specification<Poll> specification = null;
            if (params.containsKey(START_DATE_EQ)) {
                value = DtoUtils.toDate(params.get(START_DATE_EQ));
                specification = eq(value);
            } else if (params.containsKey(START_DATE_GT)) {
                value = DtoUtils.toDate(params.get(START_DATE_GT));
                specification = gt(value);
            } else if (params.containsKey(START_DATE_GTE)) {
                value = DtoUtils.toDate(params.get(START_DATE_GTE));
                specification = gte(value);
            } else if (params.containsKey(START_DATE_LT)) {
                value = DtoUtils.toDate(params.get(START_DATE_LT));
                specification = lt(value);
            } else if (params.containsKey(START_DATE_LTE)) {
                value = DtoUtils.toDate(params.get(START_DATE_LTE));
                specification = lte(value);
            }
            return specification;
        }
    }

    public static class EndDateSpecification implements DateSpecification {
        public static final String FIELD_NAME = "endDate";
        public static final String END_DATE_EQ = paramFormat(FIELD_NAME, SupportedOperations.EQ);
        public static final String END_DATE_GT = paramFormat(FIELD_NAME, SupportedOperations.GT);
        public static final String END_DATE_GTE = paramFormat(FIELD_NAME, SupportedOperations.GTE);
        public static final String END_DATE_LT = paramFormat(FIELD_NAME, SupportedOperations.LT);
        public static final String END_DATE_LTE = paramFormat(FIELD_NAME, SupportedOperations.LTE);
        private Map<String, String> params = new HashMap<>();

        private EndDateSpecification() {
        }

        public EndDateSpecification(Map<String, String> params) {
            this.params = params;
        }

        @Override
        public Specification<Poll> getSpecification() {
            return getSpecificationByParam();
        }

        @Override
        public Specification<Poll> eq(Date value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.equal(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        @Override
        public Specification<Poll> gt(Date value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.greaterThan(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        @Override
        public Specification<Poll> gte(Date value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.greaterThanOrEqualTo(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        @Override
        public Specification<Poll> lt(Date value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.lessThan(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        @Override
        public Specification<Poll> lte(Date value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.lessThan(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }

        private Specification<Poll> getSpecificationByParam() {
            Date value = null;
            Specification<Poll> specification = null;
            if (params.containsKey(END_DATE_EQ)) {
                value = DtoUtils.toDate(params.get(END_DATE_EQ));
                specification = eq(value);
            } else if (params.containsKey(END_DATE_GT)) {
                value = DtoUtils.toDate(params.get(END_DATE_GT));
                specification = gt(value);
            } else if (params.containsKey(END_DATE_GTE)) {
                value = DtoUtils.toDate(params.get(END_DATE_GTE));
                specification = gte(value);
            } else if (params.containsKey(END_DATE_LT)) {
                value = DtoUtils.toDate(params.get(END_DATE_LT));
                specification = lt(value);
            } else if (params.containsKey(END_DATE_LTE)) {
                value = DtoUtils.toDate(params.get(END_DATE_LTE));
                specification = lte(value);
            }
            return specification;
        }
    }

    public static class ActivitySpecification implements BooleanSpecification {
        public static final String FIELD_NAME = "activity";
        public static final String ACTIVITY_EQ = paramFormat(FIELD_NAME, SupportedOperations.EQ);
        private Map<String, String> params = new HashMap<>();

        private ActivitySpecification() {
        }

        public ActivitySpecification(Map<String, String> params) {
            this.params = params;
        }

        @Override
        public Specification<Poll> getSpecification() {
            Boolean value = null;
            if (params.containsKey(ACTIVITY_EQ)) {
                value = DtoUtils.toBoolean(params.get(ACTIVITY_EQ));
            }
            return eq(value);
        }

        @Override
        public Specification<Poll> eq(Boolean value) {
            return new Specification<Poll>() {
                @Override
                public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return getCriteriaBuilderIfValueExists(
                            criteriaBuilder.equal(root.get(FIELD_NAME), value),
                            value
                    );
                }
            };
        }
    }

    private interface StringSpecification {
        Specification<Poll> getSpecification(Map<String, String> params, Specification<Poll> specification);
        Specification<Poll> like(String value);
        Specification<Poll> like();
    }

    private interface DateSpecification {
        Specification<Poll> getSpecification();
        Specification<Poll> eq(Date value);
        Specification<Poll> gt(Date value);
        Specification<Poll> gte(Date value);
        Specification<Poll> lt(Date value);
        Specification<Poll> lte(Date value);
    }

    private interface BooleanSpecification {
        Specification<Poll> getSpecification();
        Specification<Poll> eq(Boolean value);
    }

    private interface SupportedOperations {
        String LIKE = "like";
        String EQ = "eq";
        String GT = "gt";
        String GTE = "gte";
        String LT = "lt";
        String LTE = "lte";
    }

    private static <T> Predicate getCriteriaBuilderIfValueExists(Predicate predicate, T value) {
        return Objects.nonNull(value) ? predicate : null;
    }

    private static String paramFormat(String field, String operation) {
        final String SEPARATOR = ".";
        return String.format("%s%s%s", field, SEPARATOR, operation);
    }
}
