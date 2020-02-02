package alexander.ivanov.polls.frontend.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public class PageUtils {
    private static Integer PAGE_DEFAULT = 0;
    private static Integer SIZE_DEFAULT = 10;
    public static String PAGE = "page";
    public static String SIZE = "size";

    public static Pageable getPageable(Map<String, String> params) {
        return PageRequest.of(getPage(params), getSize(params));
    }

    private static int getPage(Map<String, String> params) {
        return params.containsKey(PAGE) ? DtoUtils.toInteger(params.get(PAGE)) : PAGE_DEFAULT;
    }

    private static int getSize(Map<String, String> params) {
        return params.containsKey(SIZE) ? DtoUtils.toInteger(params.get(SIZE)) : SIZE_DEFAULT;
    }
}
