package humankind.tools;

import java.text.ParseException;

public class TransactionParser {
	private TransactionContext context;
	
	public TransactionParser(TransactionContext parserContext) {
		this.context = parserContext;
	}

	public TransactionContext getParserContext() {
		return context;
	}

	public IntergalacticTransaction parseTransaction(String txString) throws ParseException {
		String[] parts = txString.split(" ");
		if (parts.length == 3) {
			return new IntergalacticTransaction() {
				@Override
				public void run() {
					context.getVocabulary().put(parts[0], parts[2]);
				}
			};
		} else {
			return new PricePerUnitDefinitionTx(parts, context);
		}
	}
}
