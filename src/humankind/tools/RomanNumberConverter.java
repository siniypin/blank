package humankind.tools;

import java.util.HashMap;
import java.util.Map;

public class RomanNumberConverter {
	private static Map<String, Integer> ROMAN_2_DECIMAL = new HashMap<String, Integer>() {
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
		int length = romanLiteral.length();
		if (romanLiteral == null || length == 0)
			return 0;
		if (length == 1)
			return ROMAN_2_DECIMAL.get(romanLiteral);

		int accumulator = 0;
		int i = 0;
		while (i < romanLiteral.length()) {
			int current = ROMAN_2_DECIMAL.get(String.valueOf(romanLiteral.charAt(i)));
			i++;
			int next = (i < romanLiteral.length()) ? ROMAN_2_DECIMAL.get(String.valueOf(romanLiteral.charAt(i))) : 0;
			if (next > current) {
				accumulator += next - current;
				i++;
			} else {
				accumulator += current;
			}
		}
		return accumulator;
	}
}
