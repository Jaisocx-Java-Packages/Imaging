package com.jaisocx.tools.combiner;

import java.util.Arrays;
import java.util.List;

public class CombinerExample {

  enum LoadingHtmlElems {
    STYLE,
    LINK;
  }

  String[] domainsNames = new String[] {
      "tests-a234.example.com",
      "tests-o987234.example.com",
      "release.example.com"
  };

  public static void main( String[] args ) {

    CombinerExample locInstance = new CombinerExample();

    List<String> locDomainsNames = Arrays.asList( locInstance.domainsNames );
    List<LoadingHtmlElems> locLoadingHtmlElems = Arrays.asList( LoadingHtmlElems.values() );

    //# Example declaration of a strings array
    // List<String> stringValues = Arrays.asList( "YES", "NO" );

    //# Example declaration of boolean values.
    List<Boolean> locBooleanValues = Arrays.asList( true, false );



    // Generate all combinations in one-level loop
    List<List<Object>> combinations = Combiner.combine (
        locDomainsNames,
        locLoadingHtmlElems,
        locBooleanValues
    );

    // use all combinations less dangerous in a one-level loop
    locInstance.combinationsUsageSample ( combinations );

    // CombinerExample.printCombinations( combinations );
  }
  
  public void combinationsUsageSample ( List<List<Object>> combinations ) {
    combinations.forEach(combinationItem -> this.sampleMethod (
        (String) combinationItem.get(0),
        (LoadingHtmlElems) combinationItem.get(1),
        (boolean) combinationItem.get(2)
      )
    );
  }

  public void sampleMethod (
      String domainName,
      LoadingHtmlElems loadingHtmlElem,
      boolean boolVal
  ) {
    System.out.print( domainName + ", " );
    System.out.print( loadingHtmlElem.name() + ", " );
    System.out.print( boolVal );
    System.out.println();
  }

  public static void printCombinations (List<List<Object>> combinations) {
    combinations.forEach(combinationItem -> CombinerExample.printCombinationsItem( combinationItem ));
  }

  public static void printCombinationsItem (List<Object> combinationItem) {
    combinationItem.forEach(object -> System.out.print( object.toString() + " " ));
    System.out.println();
  }

}



