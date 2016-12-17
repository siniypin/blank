package humankind.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.text.ParseException;
import java.io.*;
import java.util.*;

public class TransactionContext {
	private BufferedReader inputReader;
	private PrintWriter outputWriter;
	private HashMap<String, String> vocabulary = new HashMap<>();
	private HashMap<String, Integer> creditsPerUnit = new HashMap<>();

	public TransactionContext(BufferedReader inputReader, BufferedWriter output) {
		this.inputReader = inputReader;
		this.outputWriter = new PrintWriter(output);
	}

	public void processInput(TransactionParser parser) {
		try {
			String line = inputReader.readLine();
			while (!line.isEmpty()) {
				try {
					parser.parseTransaction(line);
				} catch (ParseException e) {
					
				}
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
}
