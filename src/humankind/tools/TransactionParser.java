package humankind.tools;

import java.text.ParseException;

public class TransactionParser {
	private ParserContext context;
	private RomanNumberConverter romanNumberConverter = new RomanNumberConverter();

	public TransactionParser(ParserContext parserContext) {
		this.context = parserContext;
	}

	public ParserContext getParserContext() {
		return context;
	}

	public void parseTransaction(String txString) throws ParseException {
		String[] parts = txString.split(" ");
		if (parts.length == 3) {
			context.getVocabulary().put(parts[0], parts[2]);
		} else {
			StringBuffer romanLiteralBuilder = new StringBuffer();
			int i = 0;
			while (parts[i].charAt(0) > Character.valueOf('Z')) {
				romanLiteralBuilder.append(context.getVocabulary().get(parts[i]));
				i++;
			}
			int numberOfUnits = romanNumberConverter.convert(romanLiteralBuilder.toString());
			context.getPricesPerUnit().put(parts[i], Integer.parseInt(parts[i + 2]) / numberOfUnits);
		}
	}
}
