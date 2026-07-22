package com.jaisocx.tools.combiner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @usage: the Combiner class produces full set of all unique combinations of any number of input arguments, those are values sets,
 *         to use later in one-level loop, avoiding multi-level loops.
 *         I use in Java UnitTest methods, to check out, that each argument value combination doesn't lead to tested functionlity error.
 * @advantage: one-level loop implementation, more code review friendly. variable arguments number, easy to use with Strings, enums, oher data types.
 * @author: Illia Polianskyi
 * @since: 2024-07-29
 */
public class Combiner {

  public static List<List<Object>> combine(List<?>... lists) {
    List<List<Object>> combinations = new ArrayList<>();
    Object obj = new Object();
    List<Object> list = Arrays.asList( (new Object[]{ obj }) );

    long iterationsCount = lists.length;
    int[] listItemSize = new int[lists.length];

    for (int i = 0; i < lists.length; i++) {

      try {
        list = ( new ArrayList<Object>( lists[i] ) );
      } catch( ClassCastException e ) {
        throw new RuntimeException( e );
      }

      listItemSize[i] = list.size();
      iterationsCount = iterationsCount * list.size();
    }

    // since we have many lists items, we need the counter for this.
    int listItemIndex = 0;
    int listItemIndexLast = lists.length - 1;

    // for each list item, on the same position in the list, we keep the current list item inner position
    int[] listsInnerLoops = new int[lists.length];
    List<Object> oneCombination = new ArrayList<>();
    for (int i = 0; i < iterationsCount; i++) {

      // a loop inside all lists
      oneCombination.add(lists[listItemIndex].get(listsInnerLoops[listItemIndex]));

      // next arg
      listItemIndex += 1;

      // out of bounds of lists args set
      if (listItemIndex >= lists.length) {

        // start again for each lists args set
        listItemIndex = 0;

        // append the args values combination
        combinations.add(oneCombination);
        oneCombination = new ArrayList<>();

        // inner iteration inc to next index
        for (int j = listItemIndexLast; j >= 0; j--) {
          listsInnerLoops[j] += 1;
          if (listsInnerLoops[j] >= listItemSize[j]) {
            listsInnerLoops[j] = 0;
          } else {
            break;
          }
        }
      }
    }

    return combinations;
  }
}

