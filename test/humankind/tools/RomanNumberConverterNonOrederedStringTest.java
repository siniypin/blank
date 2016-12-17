package humankind.tools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RomanNumberConverterNonOrederedStringTest {
	@Parameters
	public static Object[][] data() {
		return new Object[][] { { "IV", 5 - 1 }, { "IX", 10 - 1 },
				{ "XL", 50 - 10 }, { "XC", 100 - 10 }, {"CD", 500 - 100}, {"CM", 1000 - 100} };
	}

	@Parameter(0)
	public String romanLiteral;

	@Parameter(1)
	public Integer decimalNumber;
	
	@Test
	public void shouldConvertLiteralToCorrectDecimal() {
		Assert.assertEquals(decimalNumber, new RomanNumberConverter().convert(romanLiteral));
	}
}
