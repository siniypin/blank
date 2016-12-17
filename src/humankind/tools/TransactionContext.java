package humankind.tools;

import java.io.*;
import java.util.*;

public class TransactionContext {
	private BufferedReader inputReader;
	private PrintWriter outputWriter;
	private HashMap<String, String> vocabulary = new HashMap<>();
	private HashMap<String, Integer> creditsPerUnit = new HashMap<>();

	public TransactionContext(BufferedReader inputReader, PrintWriter output) {
		this.inputReader = inputReader;
		this.outputWriter = output;
	}

	public void processInput(TransactionParser parser) {
		try {
			String line = inputReader.readLine();
			while (!line.isEmpty()) {
				IntergalacticTransaction tx = parser.parseTransaction(line);
				tx.run();
				line = inputReader.readLine();
			}
		} catch (IOException ex) {
			outputWriter.println("SNAP! can not read input data, tearing down.\n");
		}
	}

	public Map<String, String> getVocabulary() {
		return vocabulary;
	}

	public Map<String, Integer> getPricesPerUnit() {
		return creditsPerUnit;
	}

	public PrintWriter getOutputWriter() {
		return outputWriter;
	}
}
