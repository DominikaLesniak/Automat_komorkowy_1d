package sample.rules;

import java.util.Arrays;
import java.util.List;

public class ImplementedRuleNames {
    private final static List<String> RULE_NAMES = Arrays.asList("30", "60", "90", "120", "225");

    public static List<String> getRuleNames() {
        return RULE_NAMES;
    }
}