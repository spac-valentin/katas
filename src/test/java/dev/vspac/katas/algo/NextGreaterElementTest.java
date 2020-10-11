package dev.vspac.katas.algo;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

public class NextGreaterElementTest {

  @Test
  public void test1() {
    int[] arr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
    int[] actual = new NextGreaterElement().compute(arr);

    int[] expected = new int[]{1, 1, 4, 2, 1, 1, 0, 0};

    assertThat(actual, CoreMatchers.is(expected));
  }

  @Test
  public void test2() {
    int[] arr = new int[]{89,62,70,58,47,47,46,76,100,70};
    int[] actual = new NextGreaterElement().compute(arr);

    int[] expected = new int[]{8,1,5,4,3,2,1,1,0,0};

    assertThat(actual, CoreMatchers.is(expected));
  }
}
