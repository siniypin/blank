package humankind.tools;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RomanNumberConverterSequenceValidationTest {
	@Parameters
	public static Object[][] data() {
		return new Object[][] { { "VX", true }, { "LC", true }, { "DM", true }, { "IL", true }, { "IC", true },
				{ "ID", true }, { "IM", true }, { "XD", true }, { "XM", true }, { "CD", false } };
	}

	private boolean ThrowsParseException(RomanNumberConverter sut) {
		try {
			sut.convert(romanLiteral);
		} catch (ParseException x) {
			return true;
		}
		return false;
	}

	@Parameter(0)
	public String romanLiteral;

	@Parameter(1)
	public boolean expectedResult;

	@Test
	public void shouldRaiseParseExceptionOnIncorrectSequence() throws ParseException {
		Assert.assertEquals(expectedResult, ThrowsParseException(new RomanNumberConverter()));
	}
}
