package dev.vspac.katas.algo;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells
 * you how many days you would have to wait until a warmer temperature. If there is no future day
 * for which this is possible, put 0 instead.
 * <p>
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output
 * should be [1, 1, 4, 2, 1, 1, 0, 0].
 * <p>
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an
 * integer in the range [30, 100].
 * <p>
 * [73, 74, 75, 71, 69, 72, 76, 73] [(69,4), (71,3), (72,5), (73,[0,7]), (74,1), (75,2), (76,6)]
 * [(4, 69), (3,71), (5, 72), (0,73), (7,73) ]
 */

public class NextGreaterElement {

  public int[] computeNaive(int[] arr) {
    int[] result = new int[arr.length];
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] > arr[i]) {
          result[i] = j - i;
          break;
        }
      }
    }
    return result;
  }


  public int[] compute(int[] arr) {
    TreeMap<Temperature, Days> history = createHistory(arr);

    int[] result = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      Map<Temperature, Days> higherTemps = history.tailMap(Temperature.of(arr[i]), false);
      int finalI = i;

      result[i] = higherTemps.values().stream()
          .map(d -> d.getFirstAfter(finalI))
          .flatMap(Optional::stream)
          .min(Integer::compareTo)
          .map(v -> v - finalI)
          .orElse(0);
    }

    return result;
  }

  private TreeMap<Temperature, Days> createHistory(int[] arr) {
    TreeMap<Temperature, Days> history = new TreeMap<>();

    for (int i = 0; i < arr.length; i++) {
      int finalI = i;

      history.compute(Temperature.of(arr[i]), (temperature, days) -> {
        if (days == null) {
          days = new Days();
        }
        days.add(finalI);
        return days;
      });
    }

    return history;
  }

}

class Temperature implements Comparable<Temperature> {

  Integer value;

  private Temperature(Integer value) {
    this.value = value;
  }

  public static Temperature of(Integer value) {
    return new Temperature(value);
  }

  @Override
  public int compareTo(Temperature o) {
    return this.value.compareTo(o.value);
  }
}

class Days {

  TreeSet<Integer> indexes = new TreeSet<>();

  public void add(int i) {
    indexes.add(i);
  }

  public Optional<Integer> getFirstAfter(int i) {
    return Optional.ofNullable(indexes.higher(i));
  }
}
