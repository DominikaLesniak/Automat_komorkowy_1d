package sample.rules;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private List<Integer> rules;

    public Rule(String ruleName) {
        Integer ruleNumber = Integer.valueOf(ruleName);
        String binaryString = Integer.toBinaryString(ruleNumber);
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < binaryString.length(); i++)
        {
            int digit = binaryString.charAt(i) - '0';
            digits.add(0, digit);
        }
        while (digits.size() < 8) {
            digits.add(0);
        }
        this.rules = digits;
    }

    public int getNextGenerationValue(int leftCell, int middleCell, int rightCell) throws WrongNumberArgsException {
        if(leftCell == 0 && middleCell == 0 && rightCell == 0)
            return rules.get(0);
        else if(leftCell == 0 && middleCell == 0 && rightCell == 1)
            return rules.get(1);
        else if(leftCell == 0 && middleCell == 1 && rightCell == 0)
            return rules.get(2);
        else if(leftCell == 0 && middleCell == 1 && rightCell == 1)
            return rules.get(3);
        else if(leftCell == 1 && middleCell == 0 && rightCell == 0)
            return rules.get(4);
        else if(leftCell == 1 && middleCell == 0 && rightCell == 1)
            return rules.get(5);
        else if(leftCell == 1 && middleCell == 1 && rightCell == 0)
            return rules.get(6);
        else if(leftCell == 1 && middleCell == 1 && rightCell == 1)
            return rules.get(7);
        else
            throw new WrongNumberArgsException("Wrong cell value");
    }
}
