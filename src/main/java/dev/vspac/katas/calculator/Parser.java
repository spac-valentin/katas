package dev.vspac.katas.calculator;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Parser {

  private static final String DEFAULT_DELIMITER = ",";
  private static final String DELIMITER_PREFIX = "//";

  public Parser() {

  }

  public List<Integer> parse(String numbers) {
    if (isEmptyInput(numbers)) {
      return Collections.emptyList();
    }

    String delimiter = delimiter(numbers);
    numbers = removeDelimiter(numbers, delimiter);

    var operands = new LinkedList<>(Arrays.asList(numbers.split(delimiter)));

    return getIntegers(operands);
  }

  private boolean isEmptyInput(String numbers) {
    return numbers == null || numbers.isEmpty();
  }

  private List<Integer> getIntegers(List<String> operands) {
    return operands.stream()
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  private String delimiter(String numbers) {
    var delimiter = DEFAULT_DELIMITER;
    if (numbers.startsWith(DELIMITER_PREFIX)) {
      delimiter = extractDelimiter(numbers);
    }
    return delimiter;
  }

  private String extractDelimiter(String numbers) {
    return String.valueOf(numbers.charAt(DELIMITER_PREFIX.length()));
  }

  private String removeDelimiter(String numbers, String delimiter) {
    return numbers.replace(DELIMITER_PREFIX + delimiter, "");
  }


}
