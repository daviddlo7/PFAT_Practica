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
 System.out.println("Token: " + k);
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
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NO_ANCHOR,
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
		/* 77 */ YY_NOT_ACCEPT,
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
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"20:9,41,39,20:2,39,20:18,41,20,38,20:5,33,34,32,29,35,30,20,31,14,15,13,37:" +
"7,25,24,27,26,28,20:2,36:26,20,40,20:4,6,1,16,7,2,12,3,23,4,36:2,17,21,5,8," +
"11,22,9,19,10,18,36:5,20:5,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,158,
"0,1,2,3,1:12,4,5:2,1:2,5,6,5:12,7,5:8,8,9,10,8,1:2,11,12,8,13,14,15,16,17,1" +
"8,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,4" +
"3,44,45,46,47,48,49,50,51,52,53,54,5,55,56,57,58,59,60,61,62,63,64,65,66,67" +
",68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92" +
",93,94,95,96,97,98,99,100,5,101,102,103,104,105,106,107,108,109,110,111,112" +
",113,114,115")[0];

	private int yy_nxt[][] = unpackFromString(116,42,
"1,2,90,142,45,91,92,149,51,150,151,152,153,3:3,142:2,154,93,4,142,155,142,5" +
",46,6,7,8,9,10,11,12,13,14,15,142,3,52,16,4,16,-1:43,142,156,142:5,94,142:4" +
",95:3,142:4,-1,142:3,-1:12,142,95,-1:17,3:3,-1:21,3,-1:43,16,-1,16,-1,142:1" +
"2,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:12,115,95:2,142:4,-1,142:3,-1:1" +
"2,142,95,-1:5,148:19,55,148:3,55:12,148:2,55,-1,55:2,-1,44:37,20,44,50,44,-" +
"1,142:4,56,142:6,17,95:3,142:4,-1,142:3,-1:12,142,95,-1:30,19,-1:16,44:37,4" +
"7,44,50,44,-1,142:8,18,142:3,95:3,142:4,-1,142:3,-1:12,142,95,-1:6,57,-1:40" +
",142:6,21,142:5,95:3,142:4,-1,142:3,-1:12,142,95,-1:11,59,-1:35,142:9,22,14" +
"2:2,95:3,142:4,-1,142:3,-1:12,142,95,-1:9,61,-1:37,142:9,23,142:2,95:3,142:" +
"4,-1,142:3,-1:12,142,95,-1:6,63,-1:40,142:6,24,142:5,95:3,142:4,-1,142:3,-1" +
":12,142,95,-1:14,65,-1:32,142:8,25,142:3,95:3,142:4,-1,142:3,-1:12,142,95,-" +
"1:20,67,-1:26,142:12,95:3,142,26,142:2,-1,142:3,-1:12,142,95,-1:6,69,-1:40," +
"142,27,142:10,95:3,142:4,-1,142:3,-1:12,142,95,-1:8,71,-1:38,142:12,95:3,14" +
"2,28,142:2,-1,142:3,-1:12,142,95,-1:13,73,-1:33,142,29,142:10,95:3,142:4,-1" +
",142:3,-1:12,142,95,-1:25,75,-1:21,142:4,30,142:7,95:3,142:4,-1,142:3,-1:12" +
",142,95,-1:10,48,-1:36,142:4,31,142:7,95:3,142:4,-1,142:3,-1:12,142,95,-1:1" +
"0,77,-1:36,142:9,32,142:2,95:3,142:4,-1,142:3,-1:12,142,95,-1:21,49,-1:25,1" +
"42,33,142:10,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:12,95:3,142,34,142:2" +
",-1,142:3,-1:12,142,95,-1:5,142:8,35,142:3,95:3,142:4,-1,142:3,-1:12,142,95" +
",-1:5,142:9,36,142:2,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,128:19,53,128:3," +
"53:12,128:2,53,-1,53:2,-1,142:12,95:2,37,142:4,-1,142:3,-1:12,142,95,-1:5,1" +
"42:8,38,142:3,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:12,95:3,142,39,142:" +
"2,-1,142:3,-1:12,142,95,-1:5,142:8,40,142:3,95:3,142:4,-1,142:3,-1:12,142,9" +
"5,-1:5,142:8,41,142:3,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:5,42,142:6," +
"95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:12,95:3,142,43,142:2,-1,142:3,-1:" +
"12,142,95,-1:5,142:4,54,142:7,95:3,142,96,142:2,-1,142:3,-1:12,142,95,-1:5," +
"142:7,58,142:4,95:3,142:2,97,142,-1,142:3,-1:12,142,95,-1:5,142:4,60,142:7," +
"95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:9,62,142:2,95:3,142:4,-1,142:3,-1" +
":12,142,95,-1:5,142:7,64,142:4,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:12" +
",95:3,142:3,66,-1,142:3,-1:12,142,95,-1:5,142:12,95:3,142:4,-1,108,142:2,-1" +
":12,142,95,-1:5,142:4,109,142:7,95:3,68,142:3,-1,142:3,-1:12,142,95,-1:5,14" +
"2:10,146,142,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:9,145,142:2,95:3,142" +
":4,-1,142:3,-1:12,142,95,-1:5,142:12,95:3,142:2,70,142,-1,142:3,-1:12,142,9" +
"5,-1:5,142,72,142:10,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:8,110,142:3," +
"95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:3,111,142:8,95:3,142:4,-1,142:3,-" +
"1:12,142,95,-1:5,142:12,95:3,142,112,142:2,-1,142:3,-1:12,142,95,-1:5,142:1" +
"2,95:3,142:3,114,-1,142:3,-1:12,142,95,-1:5,142:3,74,142:8,95:3,142:4,-1,14" +
"2:3,-1:12,142,95,-1:5,142,116,142:10,95:3,142:4,-1,142:3,-1:12,142,95,-1:5," +
"142:7,117,142:4,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:9,120,142:2,95:3," +
"142:4,-1,142:3,-1:12,142,95,-1:5,142:4,76,142:7,95:3,142:4,-1,142:3,-1:12,1" +
"42,95,-1:5,142:12,95:3,142:3,78,-1,142:3,-1:12,142,95,-1:5,142:3,79,142:8,9" +
"5:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:9,80,142:2,95:3,142:4,-1,142:3,-1:" +
"12,142,95,-1:5,142:12,95:3,142:3,122,-1,142:3,-1:12,142,95,-1:5,142:8,123,1" +
"42:3,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:12,95:3,142:4,-1,124,142:2,-" +
"1:12,142,95,-1:5,142:5,81,142:6,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:7" +
",125,142:4,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142,82,142:10,95:3,142:4,-" +
"1,142:3,-1:12,142,95,-1:5,142:12,83,95:2,142:4,-1,142:3,-1:12,142,95,-1:5,1" +
"42:9,84,142:2,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:5,126,142:6,95:3,14" +
"2:4,-1,142:3,-1:12,142,95,-1:5,142:3,147,142:8,95:3,142:4,-1,142:3,-1:12,14" +
"2,95,-1:5,142:4,127,142:7,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:6,129,1" +
"42:5,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:5,85,142:6,95:3,142:4,-1,142" +
":3,-1:12,142,95,-1:5,142,131,142:10,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,1" +
"42:7,86,142:4,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:5,133,142:6,95:3,14" +
"2:4,-1,142:3,-1:12,142,95,-1:5,142:4,134,142:7,95:3,142:4,-1,142:3,-1:12,14" +
"2,95,-1:5,142,135,142:10,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:6,136,14" +
"2:5,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:9,137,142:2,95:3,142:4,-1,142" +
":3,-1:12,142,95,-1:5,142:12,95:3,138,142:3,-1,142:3,-1:12,142,95,-1:5,142:7" +
",87,142:4,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142,139,142:10,95:3,142:4,-" +
"1,142:3,-1:12,142,95,-1:5,142:3,140,142:8,95:3,142:4,-1,142:3,-1:12,142,95," +
"-1:5,142:8,88,142:3,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:12,95:3,142:4" +
",-1,141,142:2,-1:12,142,95,-1:5,142:5,89,142:6,95:3,142:4,-1,142:3,-1:12,14" +
"2,95,-1:5,142:12,95,121,95,142:4,-1,142:3,-1:12,142,95,-1:5,142:9,113,142:2" +
",95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:3,119,142:8,95:3,142:4,-1,142:3," +
"-1:12,142,95,-1:5,142,118,142:10,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:" +
"4,130,142:7,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:6,132,142:5,95:3,142:" +
"4,-1,142:3,-1:12,142,95,-1:5,142,98,142:10,95:3,142:4,-1,142:3,-1:12,142,95" +
",-1:5,142,99,142:3,100,142:6,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:8,10" +
"1,142:3,95:3,142:4,-1,142:2,102,-1:12,142,95,-1:5,142:5,103,142:2,104,142:2" +
",157,95:3,142:4,-1,142:3,-1:12,142,95,-1:5,142:5,105,142:6,95:3,142:4,-1,14" +
"2:3,-1:12,142,95,-1:5,142:4,144,142:7,95:3,142:4,-1,142:3,-1:12,142,95,-1:5" +
",142:12,106,95:2,142:4,-1,142:3,-1:12,142,95,-1:5,142:2,107,142:9,95:3,142:" +
"4,-1,142:3,-1:12,142,95,-1:5,142:12,143,95:2,142:4,-1,142:3,-1:12,142,95,-1" +
":4");

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
					case 48:
						{return tok(sym.PENT, null); }
					case -48:
						break;
					case 49:
						{return tok(sym.Q2STRD, null); }
					case -49:
						break;
					case 51:
						{return tok(sym.IDENT, yytext()); }
					case -50:
						break;
					case 52:
						{ throw new LexerException("Error de sintaxis (consumir token Yylex, en la linea: " + yyline);}
					case -51:
						break;
					case 54:
						{return tok(sym.IDENT, yytext()); }
					case -52:
						break;
					case 56:
						{return tok(sym.IDENT, yytext()); }
					case -53:
						break;
					case 58:
						{return tok(sym.IDENT, yytext()); }
					case -54:
						break;
					case 60:
						{return tok(sym.IDENT, yytext()); }
					case -55:
						break;
					case 62:
						{return tok(sym.IDENT, yytext()); }
					case -56:
						break;
					case 64:
						{return tok(sym.IDENT, yytext()); }
					case -57:
						break;
					case 66:
						{return tok(sym.IDENT, yytext()); }
					case -58:
						break;
					case 68:
						{return tok(sym.IDENT, yytext()); }
					case -59:
						break;
					case 70:
						{return tok(sym.IDENT, yytext()); }
					case -60:
						break;
					case 72:
						{return tok(sym.IDENT, yytext()); }
					case -61:
						break;
					case 74:
						{return tok(sym.IDENT, yytext()); }
					case -62:
						break;
					case 76:
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
					case 143:
						{return tok(sym.IDENT, yytext()); }
					case -129:
						break;
					case 144:
						{return tok(sym.IDENT, yytext()); }
					case -130:
						break;
					case 145:
						{return tok(sym.IDENT, yytext()); }
					case -131:
						break;
					case 146:
						{return tok(sym.IDENT, yytext()); }
					case -132:
						break;
					case 147:
						{return tok(sym.IDENT, yytext()); }
					case -133:
						break;
					case 148:
						{return tok(sym.IDENT, yytext()); }
					case -134:
						break;
					case 149:
						{return tok(sym.IDENT, yytext()); }
					case -135:
						break;
					case 150:
						{return tok(sym.IDENT, yytext()); }
					case -136:
						break;
					case 151:
						{return tok(sym.IDENT, yytext()); }
					case -137:
						break;
					case 152:
						{return tok(sym.IDENT, yytext()); }
					case -138:
						break;
					case 153:
						{return tok(sym.IDENT, yytext()); }
					case -139:
						break;
					case 154:
						{return tok(sym.IDENT, yytext()); }
					case -140:
						break;
					case 155:
						{return tok(sym.IDENT, yytext()); }
					case -141:
						break;
					case 156:
						{return tok(sym.IDENT, yytext()); }
					case -142:
						break;
					case 157:
						{return tok(sym.IDENT, yytext()); }
					case -143:
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
