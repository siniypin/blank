package humankind.tools;

import static org.mockito.Mockito.*;

import java.io.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionContextTest {
	@Mock
	private BufferedReader in;
	@Mock
	private BufferedWriter out;
	@Mock
	private TransactionParser parser;

	@Before
	public void initInMock() throws IOException {
		when(in.readLine()).thenReturn("lala", "lala", "");
	}

	@Test
	public void shouldIterateThroughInputStreamLines() throws IOException {
		// arrange
		TransactionContext sut = new TransactionContext(in, out);

		// act
		sut.processInput(parser);

		// assert
		verify(in, times(3)).readLine();
	}
}
