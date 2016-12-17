package humankind.tools;

import java.text.ParseException;

public class IntergalacticPriceConversionTx implements IntergalacticTransaction {
	private String[] txLines;
	private TransactionContext context;
	private RomanNumberConverter romanNumberConverter = new RomanNumberConverter();

	public IntergalacticPriceConversionTx(String[] txLines, TransactionContext transactionContext) {
		this.txLines = txLines;
		this.context = transactionContext;
	}

	@Override
	public void run() {
		StringBuffer outputBuilder = new StringBuffer();
		StringBuffer romanLiteralBuilder = new StringBuffer();
		for (int i = 3; i < txLines.length - 1; i++) {
			outputBuilder.append(txLines[i]).append(" ");
			romanLiteralBuilder.append(context.getVocabulary().get(txLines[i]));
		}

		try {
			Integer result = romanNumberConverter.convert(romanLiteralBuilder.toString());
			context.getOutputWriter().println(outputBuilder.append("is ").append(result).toString());
		} catch (ParseException e) {
			context.getOutputWriter().println("I have no idea what you are talking about");
		}
	}
}
