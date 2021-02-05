package dev.vspac.katas.calculator;

import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

  public static final int DEFAULT_VALUE = 0;

  public int add(String numbers) throws Exception {
    List<Integer> operands = new Parser().parse(numbers);

    if (operands.isEmpty()) {
      return DEFAULT_VALUE;
    }

    var negatives = filterNegatives(operands);
    if (negatives.size() > 0) {
      negativesNotPermitted(negatives);
    }

    operands = filterOutIllegalNumbers(operands);

    return sumThem(operands);
  }

  private List<Integer> filterOutIllegalNumbers(List<Integer> operands) {
    return operands.stream().filter(n -> n < 1000).collect(Collectors.toList());
  }

  private void negativesNotPermitted(List<Integer> negatives) throws Exception {
    var msg = String.format("negatives not allowed: %s", negatives);
    throw new Exception(msg);
  }

  private Integer sumThem(List<Integer> intOperands) {
    return intOperands.stream().reduce(0, Integer::sum);
  }

  private List<Integer> filterNegatives(List<Integer> intOperands) {
    return intOperands.stream()
        .filter(e -> e < 0)
        .collect(Collectors.toList());
  }
}
