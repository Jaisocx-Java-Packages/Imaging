package com.jaisocx.helpers;

import java.nio.charset.Charset;


public class JaisocxTextsHelper {
  protected static JaisocxTextsHelper instance = null;

  public static final long DATATYPE__CHARS_ARRAY = 3;
  public static final long DATATYPE__BYTES_ARRAY = 7;



  JaisocxTextsHelper() {}

  public static JaisocxTextsHelper getInstance() {
    if ( instance == null ) {
      instance = new JaisocxTextsHelper();
    }

    return instance;
  }




  public long writeArrayIntoArray (
    char[] src,
    long srcPos,
    char[] dest,
    long offsetInDest,
    long charsNumberToWrite
  ) throws Exception {

    long locSrcPos = srcPos;
    long locOffsetInDest = offsetInDest;
    long locCharsNumberToWrite = charsNumberToWrite;

    long srcLen = (long)src.length;
    long destLen = (long)dest.length;



    if ( locSrcPos >= srcLen ) {
      throw new Exception("src offset out of bounds");
    }

    if ( locOffsetInDest >= destLen ) {
      throw new Exception("dest offset out of bounds");
    }



    long offsetInSrcMax = ( locSrcPos + locCharsNumberToWrite );
    if ( offsetInSrcMax >= srcLen ) {
      locCharsNumberToWrite = ( srcLen - locSrcPos );
    }

    long offsetInDestMax = ( locOffsetInDest + locCharsNumberToWrite );
    if ( offsetInDestMax >= destLen ) {
      locCharsNumberToWrite = ( destLen - locOffsetInDest );
    }



    long conditionalLastInDestNumber = ( locOffsetInDest + locCharsNumberToWrite );



    int lociSrcPos = 1;
    int lociOffsetInDest = 1;

    char c = 1;



    while ( locOffsetInDest < conditionalLastInDestNumber ) {
      lociSrcPos = (int)locSrcPos;
      c = src[lociSrcPos];

      lociOffsetInDest = (int)locOffsetInDest;
      dest[lociOffsetInDest] = c;

      locSrcPos++;
      locOffsetInDest++;
    }



    long nextOffset = locOffsetInDest;

    return nextOffset;
  }



  public String concat( String[] inTexts ) {
    String[] locTexts = inTexts;

    char[][] locCharArrays = this.textsToCharArrays( locTexts );
    char[] joinedArray = this.concatFast( locCharArrays );
    
    String joined = String.valueOf( joinedArray );

    return joined;
  }

  public byte[] concatToBitsbuf( String[] inTexts ) {
    byte[] joinedArray = new byte[0];

    throw new RuntimeException("Not implemented");

    //return joinedArray;
  }

  public String concatCharsArrays ( 
    char[][] inCharArrays
  ) {
    char[][] locCharArrays = inCharArrays;
    char[] joinedArray = this.concatFast( locCharArrays );
    String joined = String.valueOf( joinedArray );

    return joined;
  }

  // the very base method to concat a 2 dimensional char array to a char[] array.
  // @purpose:
  //    the char[] represents String most close to storage of bytes of a text in Java programming language.
  //    The work with char[][] is fast, and one may init a const array.
  //    then easily set a char[] on a position in th const array,
  //    and to join then and converto to a String faster than from byte[] bitsbuf,
  //    since fewer use of wide choice of charsets encodings.
  public char[] concatFast ( 
    char[][] inCharArrays
  ) {
    char[][] locCharArrays = inCharArrays;
    char[] joinedArray = new char[0];
    char[] ca = new char[0];

    long textsNumber = 1;
    long totalLen = 1;
    long textLen = 1;
    long aOffset = 1;
    long bOffset = 1;
    long arrayElemId = 1;

    int itotalLen = 1;
    int iarrayElemId = 1;

    textsNumber = (long)locCharArrays.length;
    
    totalLen = this.getSumLenToConcatCharArrays (
      locCharArrays,
      this.DATATYPE__CHARS_ARRAY
    );
    itotalLen = (int)totalLen;
    joinedArray = new char[itotalLen];



    long srcPos = 0;
    aOffset = 0;
    bOffset = 0;

    
    arrayElemId = 0;
    iarrayElemId = 0;
    while ( arrayElemId < textsNumber ) {
      ca = locCharArrays[iarrayElemId];
      textLen = ca.length;
      if ( textLen == 0 ) {
        arrayElemId++;

        // iarrayElemId = (int)arrayElemId;
        iarrayElemId++;
  
        continue;
      }
      try {
        aOffset = this.writeArrayIntoArray ( 
          ca, 
          srcPos,
          joinedArray, 
          bOffset, 
          textLen
        );
      } catch (Exception e) {
        e.printStackTrace();
      }

      // aOffset = ( bOffset + textLen );
      bOffset = aOffset;


      arrayElemId++;

      // iarrayElemId = (int)arrayElemId;
      iarrayElemId++;
    }
    // finished for loop 
    // ------



    return joinedArray;
  }



  public char[][] textsToCharArrays ( 
    String[] inTexts 
  ) {
    String[] locTexts = inTexts;
    String s = "";
    long textsNumber = (long)locTexts.length;
    int itextsNumber = (int)textsNumber;
    char[][] charsArrays = new char[itextsNumber][0];
    long iterationId = 1;
    int iIterationId = 1;
    for ( iterationId = 0; iterationId < textsNumber; iterationId++ ) {
      iIterationId = (int)iterationId;
      s = locTexts[iIterationId];
      charsArrays[iIterationId] = s.toCharArray();
    }

    return charsArrays;
  }



  public byte[][] textsToBitsbufs ( 
    String[] inTexts,
    Charset encodingCharset
  ) {
    String[] locTexts = inTexts;
    String s = "";
    long textsNumber = (long)locTexts.length;
    int itextsNumber = (int)textsNumber;
    byte[][] charsArrays = new byte[itextsNumber][0];
    long iterationId = 0;
    int iIterationId = 1;
    for ( iterationId = 0; iterationId < textsNumber; iterationId++ ) {
      iIterationId = (int)iterationId;
      s = locTexts[iIterationId];
      charsArrays[iIterationId] = s.getBytes( encodingCharset );
    }

    return charsArrays;
  }



  public long getTextsNumberOfArrayToJoin ( 
    Object inCharOrByteArray,
    long inDatatype,
    long inConcatenatorLen,
    boolean inAddConcatenatorBeforeStart,
    boolean inAddConcatenatorAfterEnd
  ) {
    long joinedTextsNumber = 1;

    char[][] locCharArray = new char[0][];
    byte[][] locBytesArray = new byte[0][];

    if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
      locCharArray = (char[][])inCharOrByteArray;

    } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
      locBytesArray = (byte[][])inCharOrByteArray;

    }

    long textsNumber = 1;
    long textsNumberOneLess = 1;
    long aTextsNumber = 1;
    long bTextsNumber = 1;
    long locConcatenatorLen = 1;

    boolean withConcatenator = true;
    boolean locAddConcatenatorBeforeStart = true;
    boolean locAddConcatenatorAfterEnd = true;
    
    if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
      textsNumber = (long)locCharArray.length;

    } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
      textsNumber = (long)locBytesArray.length;

    }

    textsNumberOneLess = ( textsNumber - 1 );

    withConcatenator = ( locConcatenatorLen != 0 );

    locAddConcatenatorBeforeStart = ( withConcatenator && inAddConcatenatorBeforeStart );
    locAddConcatenatorAfterEnd    = ( withConcatenator && inAddConcatenatorAfterEnd );



    // calculating number of elems in resulting array.
    if ( withConcatenator == true ) {

      if ( locAddConcatenatorBeforeStart == true ) {
        aTextsNumber = ( textsNumber + 1 );
        bTextsNumber = aTextsNumber;
      } else {
        bTextsNumber = textsNumber;
      }

      aTextsNumber = ( bTextsNumber + textsNumberOneLess );
      bTextsNumber = aTextsNumber;

      if ( locAddConcatenatorAfterEnd == true ) {
        aTextsNumber = ( bTextsNumber + 1 );
        bTextsNumber = aTextsNumber;
      }

    } else {
      bTextsNumber = textsNumber;

    }

    joinedTextsNumber = bTextsNumber;



    return joinedTextsNumber;
  }



  // long datatype used for later when software supports access of array elems by long numbers.
  // @retVal char[][] or byte[][] as the in arg of type char[][] or byte[][] escaped as java.lang.Object
  public Object addConcatenator (
    Object inCharOrByteArray,
    Object inConcatenator_charOrByteArray,
    long inDatatype,
    boolean inAddConcatenatorBeforeStart,
    boolean inAddConcatenatorAfterEnd
  ) {
    char[][] retCharArray = new char[0][];
    byte[][] retBytesArray = new byte[0][];

    char[][] locCharArray = new char[0][];
    byte[][] locBytesArray = new byte[0][];

    char[]   locConcatCharsArray = new char[0];
    byte[]   locConcatBitsbufsArray = new byte[0];

    char[]   tmpCharsArray = new char[0];
    byte[]   tmpBitsbufsArray = new byte[0];


    if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
      locCharArray = (char[][])inCharOrByteArray;
      locConcatCharsArray = (char[])inConcatenator_charOrByteArray;

    } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
      locBytesArray = (byte[][])inCharOrByteArray;
      locConcatBitsbufsArray = (byte[])inConcatenator_charOrByteArray;

    }



    long retTextsNumber = 1;
    int iretTextsNumber = 1;
    long locConcatenatorLen = 1;
    long conditionLoopBreakNumber = 1;
    long arrayElemId = 1;
    int iarrayElemId = 1;
    long arrayLastElemId = 1;
    int iarrayLastElemId = 1;
    long joinedArrayElemId = 1;
    int ijoinedArrayElemId = 1;
    long joinedArrayLastElemId = 1;
    int ijoinedArrayLastElemId = 1;

    boolean withConcatenator = true;
    boolean locAddConcatenatorBeforeStart = true;
    boolean locAddConcatenatorAfterEnd = true;
    
    if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
      arrayLastElemId = ( (long)locCharArray.length - 1 );
      locConcatenatorLen = (long)locConcatCharsArray.length;

    } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
      arrayLastElemId = ( (long)locBytesArray.length - 1 );
      locConcatenatorLen = (long)locConcatBitsbufsArray.length;

    }
    iarrayLastElemId = (int)arrayLastElemId;



    withConcatenator = ( locConcatenatorLen != 0 );

    locAddConcatenatorBeforeStart = ( withConcatenator && inAddConcatenatorBeforeStart );
    locAddConcatenatorAfterEnd    = ( withConcatenator && inAddConcatenatorAfterEnd );



    // get number of resulting joined array
    retTextsNumber = this.getTextsNumberOfArrayToJoin ( 
      inCharOrByteArray,
      inDatatype,
      locConcatenatorLen,
      inAddConcatenatorBeforeStart,
      inAddConcatenatorAfterEnd
    );
    iretTextsNumber = (int)retTextsNumber;

    joinedArrayLastElemId = ( retTextsNumber - 1 );
    ijoinedArrayLastElemId = (int)joinedArrayLastElemId;



    // instantiating resulting array
    if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
      retCharArray = new char[iretTextsNumber][0];

    } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
      retBytesArray = new byte[iretTextsNumber][0];

    }



    arrayElemId = 0;
    iarrayElemId = 0;

    joinedArrayElemId = 0;
    ijoinedArrayElemId = 0;

    // adding to resulting array the concatentor as the first elem
    if ( locAddConcatenatorBeforeStart == true ) {

      if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
        retCharArray[ijoinedArrayElemId] = locConcatCharsArray;

      } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
        retBytesArray[ijoinedArrayElemId] = locConcatBitsbufsArray;
      }

      joinedArrayElemId++;
      ijoinedArrayElemId = (int)joinedArrayElemId;
    }



    // @purpose: in order to achieve one less if condition in for loop.
    // without trailing concatenator,
    //   we don't do the iteration for the last char[] array elem,
    //   since no trailing concatenator normally in the join func.
    if ( locAddConcatenatorAfterEnd == true ) {
      conditionLoopBreakNumber = retTextsNumber;
    } else {
      conditionLoopBreakNumber = ( retTextsNumber - 1 );
    }



    long[] zeroLenArrays = new long[iretTextsNumber];
    long len = 1;
    long numOfZeroLenArrays = 0;

    while ( joinedArrayElemId < conditionLoopBreakNumber ) {
      if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
        tmpCharsArray = locCharArray[iarrayElemId];
        len = (long)tmpCharsArray.length;

        retCharArray[ijoinedArrayElemId] = tmpCharsArray;

      } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
        tmpBitsbufsArray = locBytesArray[iarrayElemId];
        len = (long)tmpBitsbufsArray.length;

        retBytesArray[ijoinedArrayElemId] = tmpBitsbufsArray;

      }
      
      if ( len == 0 ) {
        zeroLenArrays[ijoinedArrayElemId] = 1;
        numOfZeroLenArrays++;
      }

      joinedArrayElemId++;
      arrayElemId++;

      ijoinedArrayElemId = (int)joinedArrayElemId;
      iarrayElemId = (int)arrayElemId;



      if ( withConcatenator == true ) {
        if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
          retCharArray[ijoinedArrayElemId] = locConcatCharsArray;

        } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
          retBytesArray[ijoinedArrayElemId] = locConcatBitsbufsArray;

        }

        joinedArrayElemId++;
        ijoinedArrayElemId = (int)joinedArrayElemId;
      }
    }
    // finished for loop 
    // ------



    // without concatenator, 
    //  the iterations did not include the last char[] array,
    //  now here we add the number of letters of the last text of datatype char[] of texts array to join,
    //  without the number of letters of the concatenator.
    if ( locAddConcatenatorAfterEnd == false ) {

        if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
          tmpCharsArray = locCharArray[iarrayLastElemId];
          len = (long)tmpCharsArray.length;

          retCharArray[ijoinedArrayLastElemId] = tmpCharsArray;

        } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
          tmpBitsbufsArray = locBytesArray[iarrayLastElemId];
          len = (long)tmpBitsbufsArray.length;

          retBytesArray[ijoinedArrayLastElemId] = tmpBitsbufsArray;

        }

        if ( len == 0 ) {
          zeroLenArrays[ijoinedArrayLastElemId] = 1;
          numOfZeroLenArrays++;
        }

    }



    Object retVal = new Object();
    if ( numOfZeroLenArrays == 0 ) {
      if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
        retVal = retCharArray;

      } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
        retVal = retBytesArray;

      }

      return retVal;
    }


    
    joinedArrayElemId = 0;
    ijoinedArrayElemId = 0;

    long reducedArrayElemId = 0;
    int ireducedArrayElemId = 0;

    long lenReducedCharArray = ( retTextsNumber - numOfZeroLenArrays );
    int ilenReducedCharArray = (int)lenReducedCharArray;
    char[][] reducedCharsArray = new char[0][];
    byte[][] reducedBitsbufsArray = new byte[0][];

    if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
      reducedCharsArray = new char[ilenReducedCharArray][];

    } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
      reducedBitsbufsArray = new byte[ilenReducedCharArray][];

    }

    if ( lenReducedCharArray == 0 ) {
      if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
        retVal = retCharArray;

      } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
        retVal = retBytesArray;

      }

      return retVal;
    }

    while ( joinedArrayElemId < retTextsNumber ) {
      if ( zeroLenArrays[ijoinedArrayElemId] == 1 ) {
        joinedArrayElemId++;
        ijoinedArrayElemId = (int)joinedArrayElemId;

        continue;
      }

      if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
        tmpCharsArray = retCharArray[ijoinedArrayElemId];
        reducedCharsArray[ireducedArrayElemId] = tmpCharsArray;

      } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
        tmpBitsbufsArray = retBytesArray[ijoinedArrayElemId];
        reducedBitsbufsArray[ireducedArrayElemId] = tmpBitsbufsArray;

      }

      joinedArrayElemId++;
      ijoinedArrayElemId = (int)joinedArrayElemId;

      reducedArrayElemId++;
      ireducedArrayElemId = (int)reducedArrayElemId;
    }
  


    if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
      retVal = retCharArray;

    } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
      retVal = retBytesArray;

    }



    return retVal;
  }



  public long getSumLenToConcatCharArrays ( 
    Object inCharOrBitsbufsArrays,
    long inDatatype
  ) {
    char[][] locCharArrays = new char[0][];
    char[] ca = new char[0];

    byte[][] locBitbufsArrays = new byte[0][];
    byte[] bbuf = new byte[0];

    long textsNumber = 1;
    long totalLen = 1;
    long aTotalLen = 1;
    long bTotalLen = 1;
    long textLen = 1;
    long arrayElemId = 1;
    int iarrayElemId = 1;



    if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
      locCharArrays = (char[][])inCharOrBitsbufsArrays;

    } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
      locBitbufsArrays = (byte[][])inCharOrBitsbufsArrays;

    }


    textsNumber = (long)locCharArrays.length;
    bTotalLen = 0;
    arrayElemId = 0;
    iarrayElemId = 0;
    while ( arrayElemId < textsNumber ) {

      if ( inDatatype == this.DATATYPE__CHARS_ARRAY ) {
        ca = locCharArrays[iarrayElemId];
        textLen = (long)ca.length;

      } else if ( inDatatype == this.DATATYPE__BYTES_ARRAY ) {
        bbuf = locBitbufsArrays[iarrayElemId];
        textLen = (long)bbuf.length;

      }

      aTotalLen = ( bTotalLen + textLen );
      bTotalLen = aTotalLen;

      arrayElemId++;

      // iarrayElemId = (int)arrayElemId;
      iarrayElemId++;
    }
    // finished for loop 
    // ------



    totalLen = aTotalLen;

    return totalLen;
  }



  public String join ( 
    String[] inTexts, 
    String concatenator,
    boolean inAddConcatenatorBeforeStart,
    boolean inAddConcatenatorAfterEnd
  ) {
    char[] joinedArray = this.joinStringsToCharArray (
      inTexts,
      concatenator,
      inAddConcatenatorBeforeStart,
      inAddConcatenatorAfterEnd
    );

    String joined = String.valueOf( joinedArray );

    return joined;
  }

  public char[] joinStringsToCharArray ( 
    String[] inTexts, 
    String concatenator,
    boolean inAddConcatenatorBeforeStart,
    boolean inAddConcatenatorAfterEnd
  ) {
    String[] locTexts = inTexts;
    char[][] locCharArrays = this.textsToCharArrays( locTexts );
    char[] locConcatenator = new char[0];

    if ( concatenator.length() != 0 ) {
      locConcatenator = concatenator.toCharArray();
    }

    char[] joinedArray = this.joinCharArrays (
      locCharArrays,
      locConcatenator,
      inAddConcatenatorBeforeStart,
      inAddConcatenatorAfterEnd
    );

    return joinedArray;
  }

  public char[] joinCharArrays ( 
    char[][] inCharArrays, 
    char[] inConcatenator,
    boolean inAddConcatenatorBeforeStart,
    boolean inAddConcatenatorAfterEnd
  ) {
    char[][] locCharArrays = inCharArrays;
    char[] locConcatenator = inConcatenator;

    char[] retJoinedArray = new char[0];
    char[][] locJoinedCharArrays = new char[0][0];

    long concatenatorLen = ( locConcatenator != null ) ? (long)locConcatenator.length : 0;
    boolean withConcatenator = ( concatenatorLen != 0 );



    if ( withConcatenator ) {
      locJoinedCharArrays = (char[][])this.addConcatenator (
        locCharArrays,
        locConcatenator,
        this.DATATYPE__CHARS_ARRAY,
        inAddConcatenatorBeforeStart,
        inAddConcatenatorAfterEnd
      );
    } else {
      locJoinedCharArrays = locCharArrays;
    }



    retJoinedArray = this.concatFast( 
      locJoinedCharArrays
    );



    return retJoinedArray;
  }



  public byte[] joinEncoded( String[] inTexts, String concatenator ) {
    byte[] joinedArray = new byte[0];

    throw new RuntimeException("Not implemented");

    //return joinedArray;
  }

}





