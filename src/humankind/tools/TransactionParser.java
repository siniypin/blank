package humankind.tools;

import java.io.BufferedReader;
import java.io.IOException;

public class TransactionParser {
	private BufferedReader inputReader;

	public TransactionParser(BufferedReader inputReader) {
		this.inputReader = inputReader;
	}

	public void processInput() throws IOException {
		while (inputReader.readLine() != "") {

		}
	}
}
