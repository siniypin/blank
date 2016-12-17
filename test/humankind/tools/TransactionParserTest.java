package humankind.tools;

import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionParserTest {
	@Mock
	private BufferedReader in;

	@Before
	public void initInMock() throws IOException {
		when(in.readLine()).thenReturn("lala", "lala", "");
	}

	@Test
	public void shouldIterateThroughInputStreamLines() throws IOException {
		// arrange
		TransactionParser sut = new TransactionParser(in);

		// act
		sut.processInput();

		// assert
		verify(in, times(3)).readLine();
	}
}
