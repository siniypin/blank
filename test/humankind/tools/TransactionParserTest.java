package humankind.tools;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;

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
	public void shouldParseVocabularyTx(){
		//arrange
		TransactionParser sut = new TransactionParser(new ParserContext(in, out));
		
		//act
		sut.parseTransaction("glob is I");
		
		//assert
		Assert.assertEquals("I", sut.getParserContext().getVocabulary().get("glob"));
	}
}
