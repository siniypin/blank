package humankind.tools;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;

import java.io.BufferedReader;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;
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
		TransactionContext context = new TransactionContext(in, out);
		TransactionParser sut = new TransactionParser();

		// act
		IntergalacticTransaction tx = sut.parseTransaction("glob is I", context);
		tx.run(context);

		// assert
		Assert.assertThat(tx, isA(IntergalacticTransaction.class));
		Assert.assertEquals("I", context.getVocabulary().get("glob"));
	}

	@Test
	public void shouldParseVocabularyTx2() {
		// arrange
		TransactionContext context = new TransactionContext(in, out);
		TransactionParser sut = new TransactionParser();

		// act
		IntergalacticTransaction tx = sut.parseTransaction("prok is V", context);
		tx.run(context);

		// assert
		Assert.assertThat(tx, isA(IntergalacticTransaction.class));
		Assert.assertEquals("V", context.getVocabulary().get("prok"));
	}

	@Test
	public void shouldParsePriceTx() {
		// arrange
		TransactionContext context = new TransactionContext(in, out);
		context.getVocabulary().put("glob", "I");
		TransactionParser sut = new TransactionParser();

		// act
		IntergalacticTransaction tx = sut.parseTransaction("glob glob Silver is 34 Credits", context);

		// assert
		Assert.assertThat(tx, is(instanceOf(PricePerUnitDefinitionTx.class)));
	}

	@Test
	public void shouldParsePriceConversionTx() {
		// arrange
		TransactionContext context = new TransactionContext(in, out);
		TransactionParser sut = new TransactionParser();

		// act
		IntergalacticTransaction tx = sut.parseTransaction("how much is pish tegj glob glob ?", context);

		// assert
		Assert.assertThat(tx, is(instanceOf(IntergalacticPriceConversionTx.class)));
	}
	
	@Test
	public void shouldParsePriceRequestTx() {
		// arrange
		TransactionContext context = new TransactionContext(in, out);
		TransactionParser sut = new TransactionParser();

		// act
		IntergalacticTransaction tx = sut.parseTransaction("how many Credits is glob prok Silver ?", context);

		// assert
		Assert.assertThat(tx, is(instanceOf(IntergalacticUnitsPriceRequestTx.class)));
	}
}
