package humankind.tools;

import java.io.*;

public class IntergalacticTransactionApplication {
	public static void main(String[] args) throws FileNotFoundException {
		InputStream in = System.in;
		OutputStream out = System.out;
		try {
			if (args.length > 0) {
				in = new FileInputStream(args[0]);
			}
			if (args.length > 1) {
				out = new FileOutputStream(args[1]);
			}

			TransactionContext context = new TransactionContext(new BufferedReader(new InputStreamReader(in)),
					new PrintWriter(new BufferedWriter(new OutputStreamWriter(out))));
			context.processInput(new TransactionParser());
		} catch (FileNotFoundException | SecurityException e) {
			System.out.println(e.getMessage());
		}
	}
}
