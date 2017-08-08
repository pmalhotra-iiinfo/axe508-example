package calories.tracker.app.services;


import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 *
 * Validation utility methods
 *
 */
public final class ValidationUtils {

    private ValidationUtils() {
        throw new NotImplementedException("Utility classes cannot be instantiated");
    }

    public static void assertNotBlank(String username, String message) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertMinimumLength(String username, int length, String message) {
        if (username.length() < length) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertMatches(String email, Pattern regex, String message) {
        if (!regex.matcher(email).matches()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertNotEquals(String string1, String string2, String message) {
        if (StringUtils.isNoneEmpty(string1)
                && StringUtils.isNoneEmpty(string2)
                && string1.equalsIgnoreCase(string2)) {
            throw new IllegalArgumentException(message);
        }
    }
}
