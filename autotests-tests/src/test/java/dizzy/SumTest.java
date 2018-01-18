package dizzy;

import dizzy.Calculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by dizzy on 21.12.17.
 */
public class SumTest {

    int a = 10;
    int b = 17;
    int result = 27;

    Calculator calculator = new Calculator();

    @Test
    public void testCalculationSum() {

        assertEquals(result, calculator.sum(a, b));
    }

    @Test
    public void testCalculationSumHamcrest1() {

        assertThat(result, is(calculator.sum(a, b)));
    }

    @Test
    public void testCalculationSumHamcrest2() {
        Calculator calculator = new Calculator();

        assertThat(result, equalTo(calculator.sum(a, b)));
    }

    @Test
    public void testCalculationSumHamcrest3() {
        Calculator calculator = new Calculator();

        assertThat(result, is(equalTo(calculator.sum(a, b))));
    }

    @Test
    public void testCalculationAssertJ1() {
        Calculator calculator = new Calculator();

        assertEquals(result, calculator.sum(a, b));
    }

    @Test
    public void testCalculationAssertJ2() {
        Calculator calculator = new Calculator();

        assertThat(result).isEqualTo(calculator.sum(a, b));
    }

}
