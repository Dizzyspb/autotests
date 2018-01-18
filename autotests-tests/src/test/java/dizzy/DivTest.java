package dizzy;

import dizzy.Calculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dizzy on 21.12.17.
 */
public class DivTest {

    @Test
    public void testCalculationDiv(){
        Calculator calculator = new Calculator();
        int a = 20;
        int b = 10;
        int result = 2;

        assertEquals(result, calculator.div(a, b));
    }


}
