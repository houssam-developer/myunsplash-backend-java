package org.he.myunsplash.app.utils;

import java.util.function.Predicate;

public final class CommonPredicate {

    private CommonPredicate() {}

    public static Predicate<Object> isNull = x -> x == null;
}
