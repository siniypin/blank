package humankind.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
			context.getPricesPerUnit().put(txParts[i],
					new BigDecimal(Double.toString(Integer.parseInt(txParts[i + 2]) / (double) numberOfUnits))
							.setScale(2, RoundingMode.HALF_EVEN));
		} catch (ParseException e) {
			context.getOutputWriter().println("I have no idea what you are talking about");
		} finally {
			context.getOutputWriter().flush();
		}
	}
}
