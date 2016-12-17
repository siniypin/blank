# intergalactic_converter

| Symbol | Value  |
|--------|-------:|
| I      |      1 | 
| V      |      5 | 
| X      |     10 | 
| L      |     50 | 
| C      |    100 | 
| D      |    500 | 
| M      |  1,000 |

## process

- [ ] parse roman literal to decimal number
  - [ ] Numbers are formed by combining symbols together and adding the values. For example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are placed in order of value, starting with the largest values. 
  - [ ] When smaller values precede larger values, the smaller values are subtracted from the larger values, and the result is added to the total. For example MCMXLIV = 1000 + (1000 − 100) + (50 − 10) + (5 − 1) = 1944.
  - [ ] "I" can be subtracted from "V" and "X" only. 
  - [ ] "X" can be subtracted from "L" and "C" only.
  - [ ] "C" can be subtracted from "D" and "M" only. 
  - [ ] "V", "L", and "D" can never be subtracted.
  - [ ] The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. 
  - [ ] They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX.
  - [ ] "D", "L", and "V" can never be repeated.
  - [ ] Only one small-value symbol may be subtracted from any large-value symbol.
- [ ] map intergalactic currency to roman
- [ ] set goods price in credits
- [ ] parse input
- [ ] calculate answers
