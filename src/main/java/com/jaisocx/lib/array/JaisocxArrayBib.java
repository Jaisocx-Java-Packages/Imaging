package com.jaisocx.lib.array;

import java.util.List;

public class JaisocxArrayBib {

    public static String join( List<String> textsArray, String concatenator ) {
        boolean inAddConcatenatorBeforeStart_no = false;
        boolean inAddConcatenatorAfterEnd_no = false;

        int textsArraySize = textsArray.size();
        String[] locTextsArray1 = (new String[textsArraySize]);
        String[] locTextsArray = (String[])textsArray.toArray(locTextsArray1);

        String joined = JaisocxArrayTexts.getInstance().join (
          locTextsArray,
          concatenator,
          inAddConcatenatorBeforeStart_no,
          inAddConcatenatorAfterEnd_no
        );

        return joined;
    }

    public static String joinArray( String[] textsArray, String concatenator ) {
        boolean inAddConcatenatorBeforeStart_no = false;
        boolean inAddConcatenatorAfterEnd_no = false;

        String joined = JaisocxArrayTexts.getInstance().join (
          textsArray,
          concatenator,
          inAddConcatenatorBeforeStart_no,
          inAddConcatenatorAfterEnd_no
        );

        return joined;
    }

    public static String concat( List<String> textsArray ) {
        String s = new String();
        String[] strArray = (new String[]{ s });
        String[] locTextsArray = textsArray.toArray( strArray );

        String joined = JaisocxArrayTexts.getInstance().concat( locTextsArray );

        return joined;
    }

    public static String concatArray( String[] textsArray ) {
        String joined = JaisocxArrayTexts.getInstance().concat(textsArray);

        return joined;
    }

}


