package org.he.myunsplash.app.utils;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.he.myunsplash.app.utils.CommonPredicate.isNull;
import static org.he.myunsplash.app.utils.CommonPredicate.isValidString;

public final class CommonAssertions {
    private CommonAssertions() {}

    // Main Assert FN
    private static BiFunction<Object, Function<Object, Boolean>, Boolean> assertCommon = (x, isValidFn) -> {
        System.out.println("\t|__ 🛃 🚥 assertCommon() #x: " + x);

        if (isNull(x)) { return false; }
        if (isValidFn.apply(x)) {
            // assertion success
            System.out.println("\t|__ 🛃 🏁 assertCommon() all verifications ✅ ");
            return true;
        }

        // assertion failed
        System.out.println("\t|__ 🛃 🏁 ❌ assertCommon() -> isValidFn() failed");
        return false;
    };

    public static boolean assertIsValidString(Object o) {
        System.out.println("assertIsValidString() #o: " + o);
        return assertCommon.apply(o, isValidString);
    }
}
