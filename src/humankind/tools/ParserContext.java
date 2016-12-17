package humankind.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class ParserContext {
	private BufferedReader inputReader;
	private OutputStreamWriter outputWriter;
	private HashMap<String, String> vocabulary = new HashMap<String, String>();

	public ParserContext(BufferedReader inputReader, OutputStreamWriter output) {
		this.inputReader = inputReader;
		this.outputWriter = output;
	}

	public void processInput() {
		try {
			String line = inputReader.readLine();
			while (!line.isEmpty()) {
				line = inputReader.readLine();
			}
		} catch (IOException ex) {
			try {
				outputWriter.write("SNAP! can not read input data, tearing down.\n");
				outputWriter.flush();
			} catch (IOException e) {
			}
		}
	}

	public Map<String, String> getVocabulary() {
		return vocabulary;
	}
}
