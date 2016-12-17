package humankind.tools;

import java.io.*;

public class IntergalacticTransactionApplication {
	public static void main(String[] args) throws FileNotFoundException {
		InputStream in = System.in;
		OutputStream out = System.out;
		if (args.length > 0) {
			in = new FileInputStream(args[0]);
		}
		if (args.length > 1) {
			out = new FileOutputStream(args[1]);
		}

		ParserContext parser = new ParserContext(new BufferedReader(new InputStreamReader(in)),
				new OutputStreamWriter(out));
		parser.processInput();
	}
}
