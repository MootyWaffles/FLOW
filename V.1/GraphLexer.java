/* The following code was generated by JFlex 1.4.3 on 5/8/11 2:32 AM */

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 5/8/11 2:32 AM from the specification file
 * <tt>flowg.flex</tt>
 */
class GraphLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int SQSTRING = 4;
  public static final int STRING = 2;
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\16\1\3\2\0\1\10\22\0\1\16\1\14\1\45\2\0"+
    "\1\44\1\0\1\46\1\44\1\44\1\2\1\44\1\44\1\11\1\5"+
    "\1\1\12\4\1\44\1\44\1\15\1\13\1\12\1\0\1\44\1\34"+
    "\5\6\1\36\4\6\1\41\1\6\1\31\14\6\1\44\1\47\1\44"+
    "\1\0\1\7\1\0\1\25\1\50\1\35\1\32\1\33\1\22\1\30"+
    "\1\40\1\17\2\6\1\23\1\6\1\20\1\24\1\37\1\6\1\27"+
    "\1\26\1\21\1\43\1\6\1\42\3\6\1\44\1\0\1\44\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\2\2\1\3\1\4\1\5\1\3\3\2"+
    "\1\1\1\2\1\3\13\5\1\6\1\7\1\10\1\11"+
    "\1\0\1\10\1\12\4\0\1\13\1\14\1\15\1\16"+
    "\1\17\1\5\1\20\1\5\1\21\10\5\1\22\1\23"+
    "\1\24\1\25\1\26\1\27\1\30\1\31\1\3\2\0"+
    "\1\32\1\33\3\5\1\34\4\5\1\35\1\0\2\5"+
    "\1\36\2\5\1\37\1\5\1\40\1\5\1\41\1\42"+
    "\1\43\1\44";

  private static int [] zzUnpackAction() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\51\0\122\0\173\0\244\0\173\0\173\0\315"+
    "\0\366\0\u011f\0\u0148\0\u0171\0\u019a\0\u01c3\0\u01ec\0\u0215"+
    "\0\u023e\0\u0267\0\u0290\0\u02b9\0\u02e2\0\u030b\0\u0334\0\u035d"+
    "\0\u0386\0\u03af\0\u03d8\0\173\0\173\0\u0401\0\173\0\u042a"+
    "\0\u0453\0\173\0\u047c\0\u04a5\0\u04ce\0\u04f7\0\173\0\173"+
    "\0\173\0\173\0\173\0\u0520\0\366\0\u0549\0\366\0\u0572"+
    "\0\u059b\0\u05c4\0\u05ed\0\u0616\0\u063f\0\u0668\0\u0691\0\173"+
    "\0\173\0\173\0\173\0\173\0\173\0\173\0\173\0\u04a5"+
    "\0\u06ba\0\u06e3\0\u04f7\0\366\0\u070c\0\u0735\0\u075e\0\366"+
    "\0\u0787\0\u07b0\0\u07d9\0\u0802\0\366\0\u082b\0\u0854\0\u087d"+
    "\0\366\0\u08a6\0\u08cf\0\366\0\u08f8\0\366\0\u0921\0\366"+
    "\0\366\0\366\0\366";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\6\1\7\1\10\1\6\1\11\1\4"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\2\11\1\22\1\11\1\23\1\11\1\24\2\11\1\25"+
    "\2\11\1\26\1\11\1\27\1\30\1\11\1\31\1\32"+
    "\1\33\1\6\1\34\1\35\1\4\1\11\45\36\1\37"+
    "\1\36\1\40\1\36\46\41\1\42\1\43\1\41\52\0"+
    "\1\44\1\45\52\0\1\10\1\46\47\0\1\11\1\0"+
    "\2\11\7\0\25\11\4\0\1\11\3\0\1\7\57\0"+
    "\1\47\51\0\1\50\50\0\1\51\50\0\1\52\50\0"+
    "\1\53\53\0\1\20\36\0\1\11\1\0\2\11\7\0"+
    "\1\11\1\54\1\11\1\55\21\11\4\0\1\11\4\0"+
    "\1\11\1\0\2\11\7\0\4\11\1\56\20\11\4\0"+
    "\1\11\4\0\1\11\1\0\2\11\7\0\3\11\1\57"+
    "\21\11\4\0\1\11\4\0\1\11\1\0\2\11\7\0"+
    "\2\11\1\60\22\11\4\0\1\11\4\0\1\11\1\0"+
    "\2\11\7\0\5\11\1\61\17\11\4\0\1\11\4\0"+
    "\1\11\1\0\2\11\7\0\10\11\1\62\14\11\4\0"+
    "\1\11\4\0\1\11\1\0\2\11\7\0\10\11\1\63"+
    "\14\11\4\0\1\11\4\0\1\11\1\0\2\11\7\0"+
    "\10\11\1\64\14\11\4\0\1\11\4\0\1\11\1\0"+
    "\2\11\7\0\1\65\24\11\4\0\1\11\4\0\1\11"+
    "\1\0\2\11\7\0\21\11\1\66\3\11\4\0\1\11"+
    "\4\0\1\11\1\0\2\11\7\0\7\11\1\67\15\11"+
    "\4\0\1\11\45\36\1\0\1\36\1\0\1\36\20\0"+
    "\1\70\1\71\1\72\4\0\1\73\15\0\1\74\1\0"+
    "\1\75\1\76\46\41\2\0\1\41\20\0\1\70\1\71"+
    "\1\72\4\0\1\73\16\0\1\77\1\75\1\76\3\44"+
    "\1\7\4\44\1\100\40\44\2\101\1\102\46\101\4\0"+
    "\1\103\50\0\1\11\1\0\2\11\7\0\2\11\1\104"+
    "\22\11\4\0\1\11\4\0\1\11\1\0\2\11\7\0"+
    "\5\11\1\105\17\11\4\0\1\11\4\0\1\11\1\0"+
    "\2\11\7\0\10\11\1\106\14\11\4\0\1\11\4\0"+
    "\1\11\1\0\2\11\7\0\13\11\1\107\11\11\4\0"+
    "\1\11\4\0\1\11\1\0\2\11\7\0\16\11\1\110"+
    "\6\11\4\0\1\11\4\0\1\11\1\0\2\11\7\0"+
    "\6\11\1\111\16\11\4\0\1\11\4\0\1\11\1\0"+
    "\2\11\7\0\1\112\24\11\4\0\1\11\4\0\1\11"+
    "\1\0\2\11\7\0\7\11\1\113\15\11\4\0\1\11"+
    "\4\0\1\11\1\0\2\11\7\0\1\114\24\11\4\0"+
    "\1\11\4\0\1\11\1\0\2\11\7\0\14\11\1\115"+
    "\10\11\4\0\1\11\2\101\1\116\46\101\1\0\1\7"+
    "\1\102\52\0\1\11\1\0\2\11\7\0\6\11\1\117"+
    "\16\11\4\0\1\11\4\0\1\11\1\0\2\11\7\0"+
    "\1\120\24\11\4\0\1\11\4\0\1\11\1\0\2\11"+
    "\7\0\14\11\1\121\10\11\4\0\1\11\4\0\1\11"+
    "\1\0\2\11\7\0\20\11\1\122\4\11\4\0\1\11"+
    "\4\0\1\11\1\0\2\11\7\0\1\11\1\123\23\11"+
    "\4\0\1\11\4\0\1\11\1\0\2\11\7\0\2\11"+
    "\1\124\22\11\4\0\1\11\4\0\1\11\1\0\2\11"+
    "\7\0\4\11\1\125\20\11\4\0\1\11\1\101\1\7"+
    "\1\116\46\101\4\0\1\11\1\0\2\11\7\0\2\11"+
    "\1\126\22\11\4\0\1\11\4\0\1\11\1\0\2\11"+
    "\7\0\1\11\1\127\23\11\4\0\1\11\4\0\1\11"+
    "\1\0\2\11\7\0\21\11\1\130\3\11\4\0\1\11"+
    "\4\0\1\11\1\0\2\11\7\0\2\11\1\131\22\11"+
    "\4\0\1\11\4\0\1\11\1\0\2\11\7\0\14\11"+
    "\1\132\10\11\4\0\1\11\4\0\1\11\1\0\2\11"+
    "\7\0\11\11\1\133\13\11\4\0\1\11";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2378];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\1\1\2\11\24\1\2\11\1\1\1\11"+
    "\1\0\1\1\1\11\4\0\5\11\14\1\10\11\1\1"+
    "\2\0\13\1\1\0\15\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  private GraphParser yyparser;

  private String string; /* used to build up string literals */

  public GraphLexer(java.io.Reader r, GraphParser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }

  /* track the line numbers for debugging */
  public int getLine() { return yyline; }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  GraphLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  GraphLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 140) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 36: 
          { return GraphParser.STR_T;
          }
        case 37: break;
        case 33: 
          { return GraphParser.GRAPH;
          }
        case 38: break;
        case 4: 
          { yyparser.yylval = new GraphParserVal(Integer.parseInt(yytext()));
              return GraphParser.INT;
          }
        case 39: break;
        case 6: 
          { yybegin(STRING);
              string = "";
          }
        case 40: break;
        case 28: 
          { return GraphParser.ARC_T;
          }
        case 41: break;
        case 10: 
          { yybegin(YYINITIAL);
              yyparser.yylval = new GraphParserVal(string);
              return GraphParser.STR;
          }
        case 42: break;
        case 12: 
          { return GraphParser.GTE;
          }
        case 43: break;
        case 26: 
          { yyparser.yylval = new GraphParserVal(Double.parseDouble(yytext()));
              return GraphParser.FLT;
          }
        case 44: break;
        case 2: 
          { return (int) yycharat(0);
          }
        case 45: break;
        case 15: 
          { return GraphParser.LTE;
          }
        case 46: break;
        case 25: 
          { string += "\\'";
          }
        case 47: break;
        case 31: 
          { return GraphParser.LIST_T;
          }
        case 48: break;
        case 23: 
          { string += "\\\\";
          }
        case 49: break;
        case 5: 
          { yyparser.yylval = new GraphParserVal(yytext());
              return GraphParser.ID;
          }
        case 50: break;
        case 13: 
          { return GraphParser.EQ;
          }
        case 51: break;
        case 30: 
          { return GraphParser.NODE_T;
          }
        case 52: break;
        case 19: 
          { string += "\\t";
          }
        case 53: break;
        case 27: 
          { return GraphParser.INT_T;
          }
        case 54: break;
        case 34: 
          { return GraphParser.PRINT;
          }
        case 55: break;
        case 32: 
          { return GraphParser.FLT_T;
          }
        case 56: break;
        case 24: 
          { string += "\\b";
          }
        case 57: break;
        case 16: 
          { return GraphParser.IF;
          }
        case 58: break;
        case 9: 
          { yybegin(YYINITIAL); 
              yyparser.yylval = new GraphParserVal(string);
              return GraphParser.STR;
          }
        case 59: break;
        case 21: 
          { string += "\\r";
          }
        case 60: break;
        case 11: 
          { return GraphParser.ARC;
          }
        case 61: break;
        case 17: 
          { return GraphParser.OF;
          }
        case 62: break;
        case 7: 
          { yybegin(SQSTRING); 
              string = "";
          }
        case 63: break;
        case 35: 
          { return GraphParser.WHILE;
          }
        case 64: break;
        case 8: 
          { string += yytext();
          }
        case 65: break;
        case 14: 
          { return GraphParser.NEQ;
          }
        case 66: break;
        case 1: 
          { yyparser.yylval = new GraphParserVal(yytext());
              return GraphParser.UNK;
          }
        case 67: break;
        case 20: 
          { string += "\\f";
          }
        case 68: break;
        case 22: 
          { string += "\\\"";
          }
        case 69: break;
        case 18: 
          { string += "\\n";
          }
        case 70: break;
        case 29: 
          { return GraphParser.USE;
          }
        case 71: break;
        case 3: 
          { 
          }
        case 72: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
