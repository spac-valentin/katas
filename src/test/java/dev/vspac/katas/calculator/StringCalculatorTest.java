package dev.vspac.katas.calculator;


import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StringCalculatorTest {

  @Test
  public void testAddEmptyInput() throws Exception {
    var result = new StringCalculator().add("");

    assertThat(result, is(0));
  }

  @Test
  public void testAddNullInput() throws Exception {
    var result = new StringCalculator().add(null);

    assertThat(result, is(0));
  }

  @Test
  public void testAddOneNumber() throws Exception {
    var result = new StringCalculator().add("1");

    assertThat(result, is(1));
  }

  @Test
  public void testAddOneNumberWithComma() throws Exception {
    var result = new StringCalculator().add("1,");

    assertThat(result, is(1));
  }

  static Stream<Arguments> sumOfTwoProvider() {
    return Stream.of(
        arguments("1,", 1),
        arguments("1,3", 4),
        arguments("1, 1000", 1)
        );
  }

  @ParameterizedTest
  @MethodSource("sumOfTwoProvider")
  public void testAddTwoNumbers(String input, Integer value) throws Exception {
    var result = new StringCalculator().add(input);

    assertThat(result, is(value));
  }

  @Test
  public void testAddArbitraryNumberOfOperands() throws Exception {
    var randomGen = new Random();
    var numberOfOperands = 3 + new Random().nextInt(100);
    var operands = randomGen.ints(numberOfOperands, 0, 100).boxed().collect(Collectors.toList());

    var expectedSum = operands.stream().reduce(0, Integer::sum);
    var input = operands.stream().map(Object::toString).collect(Collectors.joining(","));

    var result = new StringCalculator().add(input);

    assertThat(result, is(expectedSum));
  }

  @Test
  public void testAddNumbersWithCommaAndNewLine() throws Exception {
    var result = new StringCalculator().add("1\n,2,3");

    assertThat(result, is(6));
  }

  @Test
  public void testAddNumbersWithCustomDelimiter() throws Exception {
    var result = new StringCalculator().add("//;\n1;2");

    assertThat(result, is(3));
  }

  @Test
  public void testAddNegativeNumbersThrowsException() {
    var exception = Assertions.assertThrows(Exception.class,
        () -> new StringCalculator().add("//d\n1d2d3d-4d5d-6"));

    assertThat(exception.getMessage(), containsString("-4, -6"));
  }

}