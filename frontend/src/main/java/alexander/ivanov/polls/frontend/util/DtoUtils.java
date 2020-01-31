package alexander.ivanov.polls.frontend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DtoUtils {
    private static final Logger logger = LoggerFactory.getLogger(DtoUtils.class);

    public static Long stringToLongId(String id) {
        return Long.parseLong(id);
    }
}
