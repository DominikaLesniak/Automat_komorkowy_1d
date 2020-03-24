package sample.rules;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

public class Rule30 implements BaseRule {

    @Override
    public int getNextGenerationValue(int leftCell, int middleCell, int rightCell) throws WrongNumberArgsException {
        if(leftCell == 0 && middleCell == 0 && rightCell == 0)
            return 0;
        else if(leftCell == 0 && middleCell == 0 && rightCell == 1)
            return 1;
        else if(leftCell == 0 && middleCell == 1 && rightCell == 0)
            return 1;
        else if(leftCell == 0 && middleCell == 1 && rightCell == 1)
            return 1;
        else if(leftCell == 1 && middleCell == 0 && rightCell == 0)
            return 1;
        else if(leftCell == 1 && middleCell == 0 && rightCell == 1)
            return 0;
        else if(leftCell == 1 && middleCell == 1 && rightCell == 0)
            return 0;
        else if(leftCell == 1 && middleCell == 1 && rightCell == 1)
            return 0;
        else
            throw new WrongNumberArgsException("Wrong cell value");
    }
}
