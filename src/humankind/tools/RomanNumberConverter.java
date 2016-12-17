package humankind.tools;

import java.text.ParseException;
import java.util.*;

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

	public Integer convert(String romanLiteral) throws ParseException {
		int length = romanLiteral.length();
		if (romanLiteral == null || length == 0)
			return 0;
		if (length == 1)
			return getValueAt(romanLiteral, 0);

		return convertString(romanLiteral);
	}

	private Integer convertString(String romanLiteral) throws ParseException {
		int length = romanLiteral.length();
		int accumulator = 0;
		int i = 0;
		while (i < length) {
			int current = getValueAt(romanLiteral, i);
			int next = lookahead(romanLiteral, ++i, current);
			if (next < current) {
				accumulator += current;
			} else {
				accumulator += (next > current) ? next - current : current + next;
				if (current == next && notAllowedToBeRepeated(current))
					throw new ParseException(romanLiteral, i);

				int next2Steps = lookahead(romanLiteral, ++i, current);
				if (current == next && subtractsTwice(current, next2Steps))
					throw new ParseException(romanLiteral, i);
				int next3Steps = lookahead(romanLiteral, ++i, current);
				if (isSequenceTooLong(current, next2Steps, next3Steps))
					throw new ParseException(romanLiteral, i);
				accumulator += next2Steps + next3Steps;
				int next4Steps = lookahead(romanLiteral, ++i, current);
				if (isSequenceTooLong(next, next3Steps, next4Steps))
					throw new ParseException(romanLiteral, i);
				accumulator += next4Steps;
				i++;
			}
		}
		return accumulator;
	}

	private int lookahead(String romanLiteral, int index, int current) throws ParseException {
		int next = 0;
		if (index < romanLiteral.length()) {
			next = getValueAt(romanLiteral, index);
			if (isPairInvalid(current, next))
				throw new ParseException(romanLiteral, index);
		}
		return next;
	}

	private boolean isPairInvalid(int current, int next) {
		return next > current && (notAllowedToBeSubtracted(current) || notAllowedToBeSubtractedFrom(next, current));
	}

	private boolean notAllowedToBeSubtracted(int current) {
		return current == 5 || current == 50 || current == 500;
	}

	private boolean notAllowedToBeSubtractedFrom(int next, int current) {
		return (next - current) / current > 10;
	}

	private boolean subtractsTwice(int current, int next2Steps) {
		return next2Steps > current;
	}

	private boolean isSequenceTooLong(int current, int next2Steps, int next3Steps) {
		return current == next2Steps && current == next3Steps && next2Steps != 0 && next3Steps != 0;
	}

	private boolean notAllowedToBeRepeated(int current) {
		return !(current == 1 || current % 10 == 0);
	}

	private Integer getValueAt(String romanLiteral, int index) throws ParseException {
		Integer result = null;
		try {
			result = ROMAN_2_DECIMAL.get(String.valueOf(romanLiteral.charAt(index)));
		} finally {
			if (result == null)
				throw new ParseException(romanLiteral, index);
		}
		return result;
	}
}
