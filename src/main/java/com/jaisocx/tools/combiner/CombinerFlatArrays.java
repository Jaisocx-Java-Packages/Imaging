package com.jaisocx.tools.combiner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinerFlatArrays {

  public final int COUNTER_CORRECTION = 2;


  public ArrayList<Integer> getIntegerFlatArray (
      int val,
      int step,
      int iterationsCount,
      int min,
      int max,
      boolean offsetRemainsInMiddle
  ) {
    Integer[] flatArray = ( new Integer[]{} );
    List<Integer> list_flatArray = Arrays.asList( flatArray );
    ArrayList<Integer> arr_flatArray= ( new ArrayList<Integer>( list_flatArray ) );

    int loc_counter_correction = this.COUNTER_CORRECTION;
    int loc_iterationsCount = ( iterationsCount + loc_counter_correction );
    int counter = 1;
    int ix = 1;

    int locIterationsNumber = iterationsCount;
    int incIterationsNumber = locIterationsNumber;
    int decIterationsNumber = 0;

    int offsetMin = 0;
    int offsetMax = ( locIterationsNumber * step );

    int i_IncMax = ( val + offsetMax );
    int i_IncMin = val;

    if ( offsetRemainsInMiddle == true ) {
      locIterationsNumber = ( iterationsCount >>> 1 );

      incIterationsNumber = locIterationsNumber;
      decIterationsNumber = locIterationsNumber;

      offsetMin = ( decIterationsNumber * step );
      offsetMax = ( incIterationsNumber * step );

      i_IncMin = ( val - offsetMin );
      i_IncMax = ( val + offsetMax );

      if ( i_IncMin < min ) {
        decIterationsNumber = Integer.divideUnsigned ( ( val - min ), step );
      }

      if ( i_IncMax > max ) {
        incIterationsNumber = Integer.divideUnsigned ( ( max - val ), step );
      }

    } else {
      locIterationsNumber = iterationsCount;

      incIterationsNumber = locIterationsNumber;
      decIterationsNumber = 0;

      offsetMax = ( incIterationsNumber * step );

      i_IncMin = ( val );
      i_IncMax = ( val + offsetMax );

      if ( i_IncMin < min ) {
        decIterationsNumber = 0;

        incIterationsNumber = Integer.divideUnsigned ( ( i_IncMax - min ), step );
        val += ( ( iterationsCount - incIterationsNumber ) * step );
      }

      if ( i_IncMax > max ) {
        locIterationsNumber = Integer.divideUnsigned ( ( max - val ), step );
        incIterationsNumber = Math.min( locIterationsNumber, iterationsCount );
      }
    }



    Integer offset = Integer.valueOf(0);
    Integer arrayValue = Integer.valueOf(1);


    loc_iterationsCount = ( decIterationsNumber + loc_counter_correction );
    counter = 1;
    ci_step_dec: while ( counter < loc_iterationsCount ) {
      counter++;
      if ( counter >= loc_iterationsCount ) {
        break ci_step_dec;
      }

      ix = ( counter - loc_counter_correction );
      arrayValue = ( val - offset );
      offset += step;

      arr_flatArray.add( arrayValue );

      if ( arrayValue < min ) {
        break ci_step_dec;
      }

      if ( arrayValue > max ) {
        break ci_step_dec;
      }

      continue ci_step_dec;
    }



    loc_iterationsCount = ( incIterationsNumber + loc_counter_correction );
    offset = 0;
    counter = 1;
    ci_step_inc: while ( counter < loc_iterationsCount ) {
      counter++;
      if ( counter >= loc_iterationsCount ) {
        break ci_step_inc;
      }

      ix = ( counter - loc_counter_correction );
      arrayValue = ( val + offset );
      offset += step;

      arr_flatArray.add( arrayValue );

      if ( arrayValue < min ) {
        break ci_step_inc;
      }

      if ( arrayValue > max ) {
        break ci_step_inc;
      }

      continue ci_step_inc;
    }


    return arr_flatArray;
  }
}
