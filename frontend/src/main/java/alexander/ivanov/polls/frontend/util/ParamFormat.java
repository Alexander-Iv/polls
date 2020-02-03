package alexander.ivanov.polls.frontend.util;

public class ParamFormat {
    private static final String SEPARATOR = ".";

    public static String format(String field, String operation) {
        return String.format("%s%s%s", field, SEPARATOR, operation);
    }
}
