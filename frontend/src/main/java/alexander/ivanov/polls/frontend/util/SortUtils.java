package alexander.ivanov.polls.frontend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.stream.Collectors;

public class SortUtils {
    private static final Logger logger = LoggerFactory.getLogger(SortUtils.class);
    private static String SORT_BY_DEFAULT = "sortBy";
    private static String SORT_BY_ASC = ParamFormat.format(SORT_BY_DEFAULT, "asc");
    private static String SORT_BY_DESC = ParamFormat.format(SORT_BY_DEFAULT, "desc");

    public static Sort sortBy(Map<String, String> params) {
        String value;
        Sort pollSort = null;
        if (params.containsKey(SORT_BY_DESC)) {
            value = params.get(SORT_BY_DESC);
            logger.info("1!value = {}", value);
            pollSort = Sort.by(value).descending();
        } else if (params.containsKey(SORT_BY_ASC)) {
            value = params.getOrDefault(SORT_BY_ASC, SORT_BY_DEFAULT);
            logger.info("2!value = {}", value);
            pollSort = Sort.by(value).ascending();
        } else if (params.containsKey(SORT_BY_DEFAULT)) {
            value = params.get(SORT_BY_DEFAULT);
            logger.info("3!value = {}", value);
            pollSort = Sort.by(value).ascending();
        } else {
            pollSort = Sort.by("id").ascending();
        }
        logger.info("pollSort = {}", pollSort.get().collect(Collectors.toList()));
        return pollSort;
    }
}
