package humankind.tools;

import java.util.HashMap;
import java.util.Map;

public class RomanNumberConverter {
	private static Map<String, Integer> romanToDecimal = new HashMap<String, Integer>() {
		{
			put("I", 1);
			put("V", 5);
			put("X", 10);
			put("L", 50);
			put("C", 100);
			put("D", 500);
			put("M", 1000);
		}
	};

	public Integer convert(String romanLiteral) {
		return romanLiteral.chars().map(c -> romanToDecimal.get(String.valueOf((char)c))).sum();
	}
}
