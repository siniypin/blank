package humankind.tools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RomanNumberConverterSimpleMappingTest {
	@Parameters
	public static Object[][] data() {
		return new Object[][] { { "I", 1 }, { "V", 5 }, { "X", 10 }, { "L", 50 }, { "C", 100 }, { "D", 500 },
				{ "M", 1000 } };
	}
	
	@Parameter(0)
	public String romanLiteral;
	
	@Parameter(1)
	public Integer decimalNumber;
	
	@Test
	public void shouldConvertLiteralToCorrectDecimal(){
		Assert.assertEquals(decimalNumber, new RomanNumberConverter().convert(romanLiteral));
	}
}
