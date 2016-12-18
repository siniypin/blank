package humankind.tools;

import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public class IntergalacticUnitsPriceRequestTxTest {
	@Parameters
	public static Object[][] data() {
		return new Object[][] { { "how many Credits is glob prok Silver ?", "glob prok Silver is 68 Credits" },
				{ "how many Credits is glob prok Gold ?", "glob prok Gold is 57800 Credits" },
				{ "how many Credits is glob prok Iron ?", "glob prok Iron is 782 Credits" } };
	}

	@Parameter(0)
	public String txLine;

	@Parameter(1)
	public String expectedOutput;

	@Mock
	private BufferedReader in;

	@Mock
	private PrintWriter out;

	private TransactionContext context;

	@Before
	public void initializeMocks() {
		MockitoAnnotations.initMocks(this);
		context = new TransactionContext(in, out);
		context.getVocabulary().put("glob", "I");
		context.getVocabulary().put("prok", "V");
		context.getVocabulary().put("pish", "X");
		context.getVocabulary().put("tegj", "L");

		context.getPricesPerUnit().put("Silver",
				new BigDecimal(Double.toString((double) 34 / 2)).setScale(2, RoundingMode.HALF_EVEN));
		context.getPricesPerUnit().put("Gold",
				new BigDecimal(Double.toString((double) 57800 / 4)).setScale(2, RoundingMode.HALF_EVEN));
		context.getPricesPerUnit().put("Iron",
				new BigDecimal(Double.toString((double) 3910 / 20)).setScale(2, RoundingMode.HALF_EVEN));
	}

	@Test
	public void shouldAnswerCorrectly() {
		// arrange
		IntergalacticUnitsPriceRequestTx sut = new IntergalacticUnitsPriceRequestTx(txLine.split(" "));

		// act
		sut.run(context);

		// assert
		verify(out).println(expectedOutput);
	}
}
