package humankind.tools;

import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public class IntergalacticPriceConversionTxTest {
	@Parameters
	public static Object[][] data() {
		return new Object[][] { { "how much is pish tegj glob glob ?", "pish tegj glob glob is 42" },
				{ "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?",
						"I have no idea what you are talking about" } };
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
	}

	@Test
	public void shouldAnswerCorrectly() {
		// arrange
		IntergalacticPriceConversionTx sut = new IntergalacticPriceConversionTx(txLine.split(" "));

		// act
		sut.run(context);

		// assert
		verify(out).println(expectedOutput);
	}
}
