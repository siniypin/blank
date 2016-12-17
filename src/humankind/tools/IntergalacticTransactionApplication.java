package humankind.tools;

import java.io.*;

public class IntergalacticTransactionApplication {
	public static void main(String[] args) throws FileNotFoundException {
		InputStream in = System.in;
		OutputStream out = System.out;
		if (args.length > 0) {
			in = new FileInputStream(args[0]);// TODO: FileNotFoundException
												// SecurityException
		}
		if (args.length > 1) {
			out = new FileOutputStream(args[1]);// TODO: FileNotFoundException
												// SecurityException
		}

		TransactionContext context = new TransactionContext(new BufferedReader(new InputStreamReader(in)),
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(out))));
		TransactionParser parser = new TransactionParser(context);
		context.processInput(parser);
	}
}
