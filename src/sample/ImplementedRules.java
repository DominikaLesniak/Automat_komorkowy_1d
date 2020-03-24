package sample;

import sample.rules.*;

import java.util.Arrays;
import java.util.List;

public class ImplementedRules {
    private final static List<String> RULE_NAMES = Arrays.asList("30", "60", "90", "120", "225");

    public static BaseRule getRule(String ruleName) {
        switch (ruleName) {
            case "30":
                return new Rule30();
            case "60":
                return new Rule60();
            case "90":
                return new Rule90();
            case "120":
                return new Rule120();
            case "225":
                return new Rule225();
            default:
                return null;
        }
    }

    public static List<String> getRuleNames() {
        return RULE_NAMES;
    }
}
