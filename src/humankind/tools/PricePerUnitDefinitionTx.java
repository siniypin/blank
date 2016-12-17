package humankind.tools;

import java.text.ParseException;

public class PricePerUnitDefinitionTx implements IntergalacticTransaction {
	private TransactionContext context;
	private String[] txParts;
	private RomanNumberConverter romanNumberConverter = new RomanNumberConverter();

	public PricePerUnitDefinitionTx(String[] txParts, TransactionContext context) {
		this.txParts = txParts;
		this.context = context;
		
	}

	@Override
	public void run() {
		StringBuffer romanLiteralBuilder = new StringBuffer();
		int i = 0;
		while (txParts[i].charAt(0) > Character.valueOf('Z')) {
			romanLiteralBuilder.append(context.getVocabulary().get(txParts[i]));
			i++;
		}
		int numberOfUnits;
		try {
			numberOfUnits = romanNumberConverter.convert(romanLiteralBuilder.toString());
			context.getPricesPerUnit().put(txParts[i], Integer.parseInt(txParts[i + 2]) / numberOfUnits);
		} catch (ParseException e) {
			
		}
	}
}
