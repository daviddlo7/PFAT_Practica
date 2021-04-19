package Lexer;
import Errors.LexerException;
import Parser.sym;


public class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

private java_cup.runtime.Symbol tok(int k, Object value) {
 //System.out.println("Token: " + k);
  return new java_cup.runtime.Symbol(k, yyline, 0, value); 
}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"39:9,41:2,39:2,41,39:18,41,39,38,39:5,33,34,32,29,35,30,20,31,14,15,13,37:7" +
",25,24,27,26,28,39:2,36:26,39,40,39:4,6,1,16,7,2,12,3,23,4,36:2,17,21,5,8,1" +
"1,22,9,19,10,18,36:5,39:5,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,143,
"0,1,2,3,1:12,4,5:2,1:2,5,6,5:12,7,5:6,1:2,8,9,10,8,11,12,8,13,14,15,16,17,1" +
"8,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,4" +
"3,44,45,46,47,48,49,50,51,52,5,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67" +
",68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,5,89,90,91," +
"92,93,94,95,96,97,98,99,100,101,102")[0];

	private int yy_nxt[][] = unpackFromString(103,42,
"1,2,86,128,45,87,88,134,49,135,136,137,138,3:3,128:2,139,89,4,128,140,128,5" +
",46,6,7,8,9,10,11,12,13,14,15,128,3,50,4:2,16,-1:43,128,141,128:5,90,128:4," +
"91:3,128:4,-1,128:3,-1:12,128,91,-1:17,3:3,-1:21,3,-1:45,16,-1,128:12,91:3," +
"128:4,-1,128:3,-1:12,128,91,-1:5,128:12,111,91:2,128:4,-1,128:3,-1:12,128,9" +
"1,-1:5,128:12,91:3,128:4,53,128:3,-1:12,128,91,-1:5,44:37,20,44,48,44,-1,12" +
"8:4,54,128:6,17,91:3,128:4,-1,128:3,-1:12,128,91,-1:30,19,-1:16,44:37,47,44" +
",48,44,-1,128:8,18,128:3,91:3,128:4,-1,128:3,-1:12,128,91,-1:6,55,-1:40,128" +
":6,21,128:5,91:3,128:4,-1,128:3,-1:12,128,91,-1:11,57,-1:35,128:9,22,128:2," +
"91:3,128:4,-1,128:3,-1:12,128,91,-1:9,59,-1:37,128:9,23,128:2,91:3,128:4,-1" +
",128:3,-1:12,128,91,-1:6,61,-1:40,128:6,24,128:5,91:3,128:4,-1,128:3,-1:12," +
"128,91,-1:14,63,-1:32,128:8,25,128:3,91:3,128:4,-1,128:3,-1:12,128,91,-1:20" +
",65,-1:26,128:12,91:3,128,26,128:2,-1,128:3,-1:12,128,91,-1:6,67,-1:40,128," +
"27,128:10,91:3,128:4,-1,128:3,-1:12,128,91,-1:8,69,-1:38,128:12,91:3,128,28" +
",128:2,-1,128:3,-1:12,128,91,-1:13,71,-1:33,128,29,128:10,91:3,128:4,-1,128" +
":3,-1:12,128,91,-1:25,73,-1:21,128:4,30,128:7,91:3,128:4,-1,128:3,-1:12,128" +
",91,-1:10,42,-1:36,128:4,31,128:7,91:3,128:4,-1,128:3,-1:12,128,91,-1:10,75" +
",-1:36,128:9,32,128:2,91:3,128:4,-1,128:3,-1:12,128,91,-1:21,43,-1:25,128,3" +
"3,128:10,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:12,91:3,128,34,128:2,-1," +
"128:3,-1:12,128,91,-1:5,128:8,35,128:3,91:3,128:4,-1,128:3,-1:12,128,91,-1:" +
"5,128:9,36,128:2,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:12,91:3,128:4,51" +
",128:3,-1:12,128,91,-1:5,128:12,91:2,37,128:4,-1,128:3,-1:12,128,91,-1:5,12" +
"8:8,38,128:3,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:12,91:3,128,39,128:2" +
",-1,128:3,-1:12,128,91,-1:5,128:8,40,128:3,91:3,128:4,-1,128:3,-1:12,128,91" +
",-1:5,128:8,41,128:3,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:4,52,128:7,9" +
"1:3,128,92,128:2,-1,128:3,-1:12,128,91,-1:5,128:7,56,128:4,91:3,128:2,93,12" +
"8,-1,128:3,-1:12,128,91,-1:5,128:4,58,128:7,91:3,128:4,-1,128:3,-1:12,128,9" +
"1,-1:5,128:9,60,128:2,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:7,62,128:4," +
"91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:12,91:3,128:3,64,-1,128:3,-1:12,1" +
"28,91,-1:5,128:12,91:3,128:4,-1,104,128:2,-1:12,128,91,-1:5,128:4,105,128:7" +
",91:3,66,128:3,-1,128:3,-1:12,128,91,-1:5,128:10,132,128,91:3,128:4,-1,128:" +
"3,-1:12,128,91,-1:5,128:9,131,128:2,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,1" +
"28:12,91:3,128:2,68,128,-1,128:3,-1:12,128,91,-1:5,128,70,128:10,91:3,128:4" +
",-1,128:3,-1:12,128,91,-1:5,128:8,106,128:3,91:3,128:4,-1,128:3,-1:12,128,9" +
"1,-1:5,128:3,107,128:8,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:12,91:3,12" +
"8,108,128:2,-1,128:3,-1:12,128,91,-1:5,128:12,91:3,128:3,110,-1,128:3,-1:12" +
",128,91,-1:5,128:3,72,128:8,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128,112,1" +
"28:10,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:7,113,128:4,91:3,128:4,-1,1" +
"28:3,-1:12,128,91,-1:5,128:9,116,128:2,91:3,128:4,-1,128:3,-1:12,128,91,-1:" +
"5,128:4,74,128:7,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:12,91:3,128:3,76" +
",-1,128:3,-1:12,128,91,-1:5,128:3,77,128:8,91:3,128:4,-1,128:3,-1:12,128,91" +
",-1:5,128:9,78,128:2,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:12,91:3,128:" +
"3,118,-1,128:3,-1:12,128,91,-1:5,128:8,119,128:3,91:3,128:4,-1,128:3,-1:12," +
"128,91,-1:5,128:12,91:3,128:4,-1,120,128:2,-1:12,128,91,-1:5,128:5,79,128:6" +
",91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:7,121,128:4,91:3,128:4,-1,128:3," +
"-1:12,128,91,-1:5,128,80,128:10,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:1" +
"2,81,91:2,128:4,-1,128:3,-1:12,128,91,-1:5,128:9,82,128:2,91:3,128:4,-1,128" +
":3,-1:12,128,91,-1:5,128:5,122,128:6,91:3,128:4,-1,128:3,-1:12,128,91,-1:5," +
"128:3,133,128:8,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:4,123,128:7,91:3," +
"128:4,-1,128:3,-1:12,128,91,-1:5,128:6,124,128:5,91:3,128:4,-1,128:3,-1:12," +
"128,91,-1:5,128:5,83,128:6,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:7,84,1" +
"28:4,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:5,126,128:6,91:3,128:4,-1,12" +
"8:3,-1:12,128,91,-1:5,128:6,127,128:5,91:3,128:4,-1,128:3,-1:12,128,91,-1:5" +
",128:7,85,128:4,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:12,91,117,91,128:" +
"4,-1,128:3,-1:12,128,91,-1:5,128:9,109,128:2,91:3,128:4,-1,128:3,-1:12,128," +
"91,-1:5,128:3,115,128:8,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128,114,128:1" +
"0,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:4,125,128:7,91:3,128:4,-1,128:3" +
",-1:12,128,91,-1:5,128,94,128:10,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128," +
"95,128:3,96,128:6,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:8,97,128:3,91:3" +
",128:4,-1,128:2,98,-1:12,128,91,-1:5,128:5,99,128:2,100,128:2,142,91:3,128:" +
"4,-1,128:3,-1:12,128,91,-1:5,128:5,101,128:6,91:3,128:4,-1,128:3,-1:12,128," +
"91,-1:5,128:4,130,128:7,91:3,128:4,-1,128:3,-1:12,128,91,-1:5,128:12,102,91" +
":2,128:4,-1,128:3,-1:12,128,91,-1:5,128:2,103,128:9,91:3,128:4,-1,128:3,-1:" +
"12,128,91,-1:5,128:12,129,91:2,128:4,-1,128:3,-1:12,128,91,-1:4");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException, 
LexerException

		{
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

{return tok(sym.EOF, null); }
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return tok(sym.IDENT, yytext()); }
					case -3:
						break;
					case 3:
						{return tok(sym.CINT,  new Integer(yytext())); }
					case -4:
						break;
					case 4:
						{ throw new LexerException("Error de sintaxis (consumir token Yylex, en la linea: " + yyline);}
					case -5:
						break;
					case 5:
						{return tok(sym.PC, null); }
					case -6:
						break;
					case 6:
						{return tok(sym.IGUAL, null); }
					case -7:
						break;
					case 7:
						{return tok(sym.MENOR, null); }
					case -8:
						break;
					case 8:
						{return tok(sym.MAYOR, null); }
					case -9:
						break;
					case 9:
						{return tok(sym.MAS, null); }
					case -10:
						break;
					case 10:
						{return tok(sym.MENOS, null); }
					case -11:
						break;
					case 11:
						{return tok(sym.DIV, null); }
					case -12:
						break;
					case 12:
						{return tok(sym.MUL, null); }
					case -13:
						break;
					case 13:
						{return tok(sym.PAREN, null); }
					case -14:
						break;
					case 14:
						{return tok(sym.TESIS, null); }
					case -15:
						break;
					case 15:
						{return tok(sym.COMA, null); }
					case -16:
						break;
					case 16:
						{ }
					case -17:
						break;
					case 17:
						{return tok(sym.IF, null); }
					case -18:
						break;
					case 18:
						{return tok(sym.OR, null); }
					case -19:
						break;
					case 19:
						{return tok(sym.ASIG, null); }
					case -20:
						break;
					case 20:
						{return tok(sym.CSTR, yytext()); }
					case -21:
						break;
					case 21:
						{return tok(sym.END, null); }
					case -22:
						break;
					case 22:
						{return tok(sym.TYPE, new Integer(0)); }
					case -23:
						break;
					case 23:
						{return tok(sym.NOT, null); }
					case -24:
						break;
					case 24:
						{return tok(sym.AND, null); }
					case -25:
						break;
					case 25:
						{return tok(sym.TYPE, new Integer(2)); }
					case -26:
						break;
					case 26:
						{return tok(sym.TYPE, new Integer(1)); }
					case -27:
						break;
					case 27:
						{return tok(sym.ELSE, null); }
					case -28:
						break;
					case 28:
						{return tok(sym.DECL, null); }
					case -29:
						break;
					case 29:
						{return tok(sym.CLOG,  new Boolean(true)); }
					case -30:
						break;
					case 30:
						{return tok(sym.THEN, null); }
					case -31:
						break;
					case 31:
						{return tok(sym.BEGIN, null); }
					case -32:
						break;
					case 32:
						{return tok(sym.PRINT, null); }
					case -33:
						break;
					case 33:
						{return tok(sym.CLOG, new Boolean(false)); }
					case -34:
						break;
					case 34:
						{return tok(sym.UNTIL, null); }
					case -35:
						break;
					case 35:
						{return tok(sym.Q2STR, null); }
					case -36:
						break;
					case 36:
						{return tok(sym.REPEAT, null); }
					case -37:
						break;
					case 37:
						{return tok(sym.PROG, null); }
					case -38:
						break;
					case 38:
						{return tok(sym.INT2STR, null); }
					case -39:
						break;
					case 39:
						{return tok(sym.TYPE, new Integer(3)); }
					case -40:
						break;
					case 40:
						{return tok(sym.NUM, null); }
					case -41:
						break;
					case 41:
						{return tok(sym.DEN, null); }
					case -42:
						break;
					case 42:
						{return tok(sym.PENT, null); }
					case -43:
						break;
					case 43:
						{return tok(sym.Q2STRD, null); }
					case -44:
						break;
					case 45:
						{return tok(sym.IDENT, yytext()); }
					case -45:
						break;
					case 46:
						{ throw new LexerException("Error de sintaxis (consumir token Yylex, en la linea: " + yyline);}
					case -46:
						break;
					case 47:
						{return tok(sym.CSTR, yytext()); }
					case -47:
						break;
					case 49:
						{return tok(sym.IDENT, yytext()); }
					case -48:
						break;
					case 50:
						{ throw new LexerException("Error de sintaxis (consumir token Yylex, en la linea: " + yyline);}
					case -49:
						break;
					case 52:
						{return tok(sym.IDENT, yytext()); }
					case -50:
						break;
					case 54:
						{return tok(sym.IDENT, yytext()); }
					case -51:
						break;
					case 56:
						{return tok(sym.IDENT, yytext()); }
					case -52:
						break;
					case 58:
						{return tok(sym.IDENT, yytext()); }
					case -53:
						break;
					case 60:
						{return tok(sym.IDENT, yytext()); }
					case -54:
						break;
					case 62:
						{return tok(sym.IDENT, yytext()); }
					case -55:
						break;
					case 64:
						{return tok(sym.IDENT, yytext()); }
					case -56:
						break;
					case 66:
						{return tok(sym.IDENT, yytext()); }
					case -57:
						break;
					case 68:
						{return tok(sym.IDENT, yytext()); }
					case -58:
						break;
					case 70:
						{return tok(sym.IDENT, yytext()); }
					case -59:
						break;
					case 72:
						{return tok(sym.IDENT, yytext()); }
					case -60:
						break;
					case 74:
						{return tok(sym.IDENT, yytext()); }
					case -61:
						break;
					case 76:
						{return tok(sym.IDENT, yytext()); }
					case -62:
						break;
					case 77:
						{return tok(sym.IDENT, yytext()); }
					case -63:
						break;
					case 78:
						{return tok(sym.IDENT, yytext()); }
					case -64:
						break;
					case 79:
						{return tok(sym.IDENT, yytext()); }
					case -65:
						break;
					case 80:
						{return tok(sym.IDENT, yytext()); }
					case -66:
						break;
					case 81:
						{return tok(sym.IDENT, yytext()); }
					case -67:
						break;
					case 82:
						{return tok(sym.IDENT, yytext()); }
					case -68:
						break;
					case 83:
						{return tok(sym.IDENT, yytext()); }
					case -69:
						break;
					case 84:
						{return tok(sym.IDENT, yytext()); }
					case -70:
						break;
					case 85:
						{return tok(sym.IDENT, yytext()); }
					case -71:
						break;
					case 86:
						{return tok(sym.IDENT, yytext()); }
					case -72:
						break;
					case 87:
						{return tok(sym.IDENT, yytext()); }
					case -73:
						break;
					case 88:
						{return tok(sym.IDENT, yytext()); }
					case -74:
						break;
					case 89:
						{return tok(sym.IDENT, yytext()); }
					case -75:
						break;
					case 90:
						{return tok(sym.IDENT, yytext()); }
					case -76:
						break;
					case 91:
						{return tok(sym.IDENT, yytext()); }
					case -77:
						break;
					case 92:
						{return tok(sym.IDENT, yytext()); }
					case -78:
						break;
					case 93:
						{return tok(sym.IDENT, yytext()); }
					case -79:
						break;
					case 94:
						{return tok(sym.IDENT, yytext()); }
					case -80:
						break;
					case 95:
						{return tok(sym.IDENT, yytext()); }
					case -81:
						break;
					case 96:
						{return tok(sym.IDENT, yytext()); }
					case -82:
						break;
					case 97:
						{return tok(sym.IDENT, yytext()); }
					case -83:
						break;
					case 98:
						{return tok(sym.IDENT, yytext()); }
					case -84:
						break;
					case 99:
						{return tok(sym.IDENT, yytext()); }
					case -85:
						break;
					case 100:
						{return tok(sym.IDENT, yytext()); }
					case -86:
						break;
					case 101:
						{return tok(sym.IDENT, yytext()); }
					case -87:
						break;
					case 102:
						{return tok(sym.IDENT, yytext()); }
					case -88:
						break;
					case 103:
						{return tok(sym.IDENT, yytext()); }
					case -89:
						break;
					case 104:
						{return tok(sym.IDENT, yytext()); }
					case -90:
						break;
					case 105:
						{return tok(sym.IDENT, yytext()); }
					case -91:
						break;
					case 106:
						{return tok(sym.IDENT, yytext()); }
					case -92:
						break;
					case 107:
						{return tok(sym.IDENT, yytext()); }
					case -93:
						break;
					case 108:
						{return tok(sym.IDENT, yytext()); }
					case -94:
						break;
					case 109:
						{return tok(sym.IDENT, yytext()); }
					case -95:
						break;
					case 110:
						{return tok(sym.IDENT, yytext()); }
					case -96:
						break;
					case 111:
						{return tok(sym.IDENT, yytext()); }
					case -97:
						break;
					case 112:
						{return tok(sym.IDENT, yytext()); }
					case -98:
						break;
					case 113:
						{return tok(sym.IDENT, yytext()); }
					case -99:
						break;
					case 114:
						{return tok(sym.IDENT, yytext()); }
					case -100:
						break;
					case 115:
						{return tok(sym.IDENT, yytext()); }
					case -101:
						break;
					case 116:
						{return tok(sym.IDENT, yytext()); }
					case -102:
						break;
					case 117:
						{return tok(sym.IDENT, yytext()); }
					case -103:
						break;
					case 118:
						{return tok(sym.IDENT, yytext()); }
					case -104:
						break;
					case 119:
						{return tok(sym.IDENT, yytext()); }
					case -105:
						break;
					case 120:
						{return tok(sym.IDENT, yytext()); }
					case -106:
						break;
					case 121:
						{return tok(sym.IDENT, yytext()); }
					case -107:
						break;
					case 122:
						{return tok(sym.IDENT, yytext()); }
					case -108:
						break;
					case 123:
						{return tok(sym.IDENT, yytext()); }
					case -109:
						break;
					case 124:
						{return tok(sym.IDENT, yytext()); }
					case -110:
						break;
					case 125:
						{return tok(sym.IDENT, yytext()); }
					case -111:
						break;
					case 126:
						{return tok(sym.IDENT, yytext()); }
					case -112:
						break;
					case 127:
						{return tok(sym.IDENT, yytext()); }
					case -113:
						break;
					case 128:
						{return tok(sym.IDENT, yytext()); }
					case -114:
						break;
					case 129:
						{return tok(sym.IDENT, yytext()); }
					case -115:
						break;
					case 130:
						{return tok(sym.IDENT, yytext()); }
					case -116:
						break;
					case 131:
						{return tok(sym.IDENT, yytext()); }
					case -117:
						break;
					case 132:
						{return tok(sym.IDENT, yytext()); }
					case -118:
						break;
					case 133:
						{return tok(sym.IDENT, yytext()); }
					case -119:
						break;
					case 134:
						{return tok(sym.IDENT, yytext()); }
					case -120:
						break;
					case 135:
						{return tok(sym.IDENT, yytext()); }
					case -121:
						break;
					case 136:
						{return tok(sym.IDENT, yytext()); }
					case -122:
						break;
					case 137:
						{return tok(sym.IDENT, yytext()); }
					case -123:
						break;
					case 138:
						{return tok(sym.IDENT, yytext()); }
					case -124:
						break;
					case 139:
						{return tok(sym.IDENT, yytext()); }
					case -125:
						break;
					case 140:
						{return tok(sym.IDENT, yytext()); }
					case -126:
						break;
					case 141:
						{return tok(sym.IDENT, yytext()); }
					case -127:
						break;
					case 142:
						{return tok(sym.IDENT, yytext()); }
					case -128:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
