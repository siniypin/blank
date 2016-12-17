package humankind.tools;

import static org.hamcrest.CoreMatchers.*;

import java.io.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionParserTest {
	@Mock
	private BufferedReader in;
	@Mock
	private PrintWriter out;

	@Test
	public void shouldParseVocabularyTx() {
		// arrange
		TransactionParser sut = new TransactionParser(new TransactionContext(in, out));

		// act
		IntergalacticTransaction tx = sut.parseTransaction("glob is I");
		tx.run();

		// assert
		Assert.assertThat(tx, isA(IntergalacticTransaction.class));
		Assert.assertEquals("I", sut.getParserContext().getVocabulary().get("glob"));
	}

	@Test
	public void shouldParseVocabularyTx2() {
		// arrange
		TransactionParser sut = new TransactionParser(new TransactionContext(in, out));

		// act
		IntergalacticTransaction tx = sut.parseTransaction("prok is V");
		tx.run();

		// assert
		Assert.assertThat(tx, isA(IntergalacticTransaction.class));
		Assert.assertEquals("V", sut.getParserContext().getVocabulary().get("prok"));
	}

	@Test
	public void shouldParsePriceTx() {
		// arrange
		TransactionContext context = new TransactionContext(in, out);
		context.getVocabulary().put("glob", "I");
		TransactionParser sut = new TransactionParser(context);

		// act
		IntergalacticTransaction tx = sut.parseTransaction("glob glob Silver is 34 Credits");
		tx.run();

		// assert
		Assert.assertThat(tx, is(instanceOf(PricePerUnitDefinitionTx.class)));
		Assert.assertEquals(Integer.valueOf(34 / 2), sut.getParserContext().getPricesPerUnit().get("Silver"));
	}

	@Test
	public void shouldParsePriceConversionTx() {
		// arrange
		TransactionContext context = new TransactionContext(in, out);
		TransactionParser sut = new TransactionParser(context);

		// act
		IntergalacticTransaction tx = sut.parseTransaction("how much is pish tegj glob glob ?");

		// assert
		Assert.assertThat(tx, is(instanceOf(IntergalacticPriceConversionTx.class)));
	}
}
