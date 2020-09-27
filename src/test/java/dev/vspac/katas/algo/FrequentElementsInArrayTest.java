package dev.vspac.katas.algo;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.Set;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

public class FrequentElementsInArrayTest {

  @Test
  public void most2FreqElements() {
    int arr[] = {1, 1, 1, 2, 2, 3};
    int k = 2;
    Set<Entry<Integer, Integer>> expected = Set.of(
        new AbstractMap.SimpleEntry<>(1, 3),
        new AbstractMap.SimpleEntry<>(2, 2)
    );

    Set<Entry<Integer, Integer>> actual = new FrequentElementsInArray()
        .findKMostFrequentElements(arr, k);

    assertThat(actual, CoreMatchers.is(expected));
  }

}