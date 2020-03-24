package sample.rules;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

public interface BaseRule {
    int getNextGenerationValue(int leftCell, int middleCell, int rightCell) throws WrongNumberArgsException;
}
