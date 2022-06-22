package org.he.myunsplash.app.utils;

import java.util.function.Function;
import java.util.function.Predicate;

public final class CommonPredicate {
    private CommonPredicate() {}

    public static boolean isNull(Object o) {
        return (o == null);
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    public static boolean isString(Object s) {
        try {
            return s.getClass() == String.class;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isNotEmptyString(String s) {
        return !s.isEmpty();
    }

    public static Function<Object, Boolean> isValidString = o ->  {
        // if left is true we can cast in right
        return isString(o) && isNotEmptyString((String) o);
    };


}
