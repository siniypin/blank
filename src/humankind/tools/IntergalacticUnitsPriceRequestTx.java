package humankind.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;

public class IntergalacticUnitsPriceRequestTx implements IntergalacticTransaction {
	private String[] txParts;
	private RomanNumberConverter romanNumberConverter = new RomanNumberConverter();

	public IntergalacticUnitsPriceRequestTx(String[] txParts) {
		this.txParts = txParts;
	}

	@Override
	public void run(TransactionContext context) {
		try {
			String goods = txParts[txParts.length - 2];
			throwUnknownGoodsError(goods, context);
			BigDecimal pricePerUnit = context.getPricesPerUnit().get(goods);

			StringBuffer outputBuilder = new StringBuffer();
			StringBuffer romanLiteralBuilder = new StringBuffer();
			for (int i = 4; i < txParts.length - 2; i++) {
				outputBuilder.append(txParts[i]).append(" ");
				romanLiteralBuilder.append(context.getVocabulary().get(txParts[i]));
			}

			Integer numberOfUnits = romanNumberConverter.convert(romanLiteralBuilder.toString());
			BigDecimal result = pricePerUnit.multiply(BigDecimal.valueOf(numberOfUnits)).setScale(0,
					RoundingMode.HALF_EVEN);
			context.getOutputWriter().println(outputBuilder.append(goods).append(" is ").append(result.toPlainString())
					.append(" Credits").toString());
		} catch (ParseException e) {
			context.getOutputWriter().println("I have no idea what you are talking about");
		} finally {
			context.getOutputWriter().flush();
		}
	}

	private void throwUnknownGoodsError(String goods, TransactionContext context) throws ParseException {
		if (!context.getPricesPerUnit().containsKey(goods)) {
			throw new ParseException(goods, 0);
		}
	}
}
