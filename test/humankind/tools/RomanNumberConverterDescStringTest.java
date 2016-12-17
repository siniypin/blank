package humankind.tools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RomanNumberConverterDescStringTest {
	@Parameters
	public static Object[][] data() {
		return new Object[][] { { "", 0 }, { "VI", 1 + 5 }, { "XV", 10 + 5 },
				{ "MDCLXVI", 1000 + 500 + 100 + 50 + 10 + 5 + 1 } };
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
