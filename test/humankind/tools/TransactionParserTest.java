package humankind.tools;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionParserTest {
	@Mock
	private BufferedReader in;
	@Mock
	private OutputStreamWriter out;

	@Test
	public void shouldParseVocabularyTx() throws ParseException {
		// arrange
		TransactionParser sut = new TransactionParser(new ParserContext(in, out));

		// act
		sut.parseTransaction("glob is I");

		// assert
		Assert.assertEquals("I", sut.getParserContext().getVocabulary().get("glob"));
	}

	@Test
	public void shouldParseVocabularyTx2() throws ParseException {
		// arrange
		TransactionParser sut = new TransactionParser(new ParserContext(in, out));

		// act
		sut.parseTransaction("prok is V");

		// assert
		Assert.assertEquals("V", sut.getParserContext().getVocabulary().get("prok"));
	}

	@Test
	public void shouldParsePriceTx() throws ParseException {
		// arrange
		ParserContext context = new ParserContext(in, out);
		context.getVocabulary().put("glob", "I");
		TransactionParser sut = new TransactionParser(context);
		

		// act
		sut.parseTransaction("glob glob Silver is 34 Credits");

		// assert
		Assert.assertEquals(Integer.valueOf(34 / 2), sut.getParserContext().getPricesPerUnit().get("Silver"));
	}
}
