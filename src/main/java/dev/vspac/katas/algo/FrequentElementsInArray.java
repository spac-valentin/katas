package dev.vspac.katas.algo;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Example 1: Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2]
 * <p>
 * Example 2: Input: nums = [1], k = 1 Output: [1]
 * <p>
 * Note: You may assume k is always valid, 1 ≤ k ≤ number of unique elements. Your algorithm's time
 * complexity must be better than O(n log n), where n is the array's size.
 * <p>
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements
 * is unique. You can return the answer in any order.
 */
public class FrequentElementsInArray {

  private Map<Integer, Integer> countIt(int[] arr) {
    Map<Integer, Integer> counts = new HashMap<>();
    for (int i : arr) {
      counts.merge(i, 1, Integer::sum);
    }

    return counts;
  }

  private Set<Entry<Integer, Integer>> sortAndSelect(Map<Integer, Integer> counts, int k) {
    Map<Integer, Integer> sorted = counts
        .entrySet()
        .stream()
        // Heap Sort, O(n log n)
        .sorted(comparingByValue((n1, n2) -> n2 - n1))
        .collect(
            toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));

    return sorted.entrySet()
        .stream()
        .limit(k)
        .collect(Collectors.toSet());
  }

  public Set<Entry<Integer, Integer>> findKMostFrequentElements(int[] arr, int k) {
    return sortAndSelect(countIt(arr), k);
  }

}
