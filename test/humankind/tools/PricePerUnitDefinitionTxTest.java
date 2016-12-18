package humankind.tools;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PricePerUnitDefinitionTxTest {
	@Mock
	private BufferedReader in;

	@Mock
	private PrintWriter out;

	private TransactionContext context;

	@Before
	public void initializeContext() {
		context = new TransactionContext(in, out);
		context.getVocabulary().put("glob", "I");
		context.getVocabulary().put("prok", "V");
		context.getVocabulary().put("pish", "X");
		context.getVocabulary().put("tegj", "L");
	}

	@Test
	public void shouldAnswerCorrectly() {
		// arrange
		PricePerUnitDefinitionTx sut = new PricePerUnitDefinitionTx("glob glob Silver is 34 Credits".split(" "),
				context);

		// act
		sut.run();

		// assert
		Assert.assertTrue(new BigDecimal("17.0").compareTo(context.getPricesPerUnit().get("Silver")) == 0);
	}
}
