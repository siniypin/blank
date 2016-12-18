package humankind.tools;

public class TransactionParser {
	public IntergalacticTransaction parseTransaction(String txString, TransactionContext context) {
		String[] parts = txString.split(" ");
		if (parts.length == 3) {
			return new IntergalacticTransaction() {
				@Override
				public void run(TransactionContext context) {
					context.getVocabulary().put(parts[0], parts[2]);
				}
			};
		} else if (parts[parts.length - 1].equals("?")) {
			return parseQuestions(parts);
		} else if (parts[parts.length - 1].equals("Credits")) {
			return new PricePerUnitDefinitionTx(parts);
		} else {
			return new IntergalacticTransaction() {
				@Override
				public void run(TransactionContext context) {
					context.getOutputWriter().println("Don't know how to parse this transaction. Was that Scottish?");
				}
			};
		}
	}

	private IntergalacticTransaction parseQuestions(String[] parts) {
		return parts[1].equals("much") ? new IntergalacticPriceConversionTx(parts)
				: new IntergalacticUnitsPriceRequestTx(parts);
	}
}
