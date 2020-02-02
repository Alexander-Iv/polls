package alexander.ivanov.polls.frontend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DtoUtils {
    private static final Logger logger = LoggerFactory.getLogger(DtoUtils.class);

    public static Long stringToLongId(String id) {
        return Long.parseLong(id);
    }

    public static Date toDate(String date) {
        LocalDateTime parsedDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        ZoneOffset zoneOffset = OffsetDateTime.now().getOffset();
        Instant dateInstant = parsedDate.toInstant(zoneOffset);
        return Date.from(dateInstant);
    }

    public static Boolean toBoolean(String value) {
        return Boolean.parseBoolean(value);
    }

    public static Integer toInteger(String value) {
        return Integer.parseInt(value);
    }
}
