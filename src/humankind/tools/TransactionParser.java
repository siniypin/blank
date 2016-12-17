package humankind.tools;

public class TransactionParser {
	private ParserContext context;

	public TransactionParser(ParserContext parserContext) {
		this.context = parserContext;
	}

	public ParserContext getParserContext() {
		return context;
	}

	public void parseTransaction(String txString) {
		context.getVocabulary().put("glob", "I");
	}
}
