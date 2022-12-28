package com.johncsinclair.consoletable;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.johncsinclair.consoletable.ColumnFormat.Aligned;


/**
 * JUnit 5 Tests for ConsoleTable
 *
 * To display monospaced box-drawing characters, In Eclipse, in Run | Run Configurations... , in the Common tab, set Encoding to Other UTF-8,
 * or set the Project | Properties | Text File Encoding to UTF-8, so that it is inherited automatically by the Run Configurations.
 *
 * @see <a href="https://stackoverflow.com/questions/28887506/how-to-make-eclipse-junit-stacktrace-use-non-proportional-font">How to make eclipse junit stacktrace use non-proportional font?</a>
 * 
 * @author Copyright (c) John C Sinclair 2021
 */

class ConsoleTableTest {

	@Test
	void testExample1() {
        ConsoleTable table = new ConsoleTable();
        table.setHeaders("One", "Two", "Three");	//optional - if not used then there will be no header and horizontal lines
        table.addRow(new java.math.BigDecimal("98.76"), "broccoli", "flexible");
        table.addRow(new java.math.BigDecimal("1.23"), "announcement", "reflection");
        table.addRow(new java.math.BigDecimal("1234.45"), null, java.time.LocalDateTime.parse("2020-12-24T14:15:16.987") );
        table.addRow( java.math.BigDecimal.ZERO, "pleasant", "wild");

        String expected = 
    		"┌─────────┬──────────────┬─────────────────────────┐"+"\n"+
			"│     One │          Two │                   Three │"+"\n"+
			"├─────────┼──────────────┼─────────────────────────┤"+"\n"+
			"│   98.76 │     broccoli │                flexible │"+"\n"+
			"│    1.23 │ announcement │              reflection │"+"\n"+
			"│ 1234.45 │              │ 2020-12-24T14:15:16.987 │"+"\n"+
			"│       0 │     pleasant │                    wild │"+"\n"+
			"└─────────┴──────────────┴─────────────────────────┘"+"\n";
		assertLinesMatch( Arrays.asList(expected.split("\n")),
				Arrays.asList(table.toString().split("\n")), "four");
	}
	
	@Test
	void testStylePositions() {
		class UniqueStyle implements Style {
			// a different character in every position to check each is correct
		    String[][] tablePattern = new String[][] {
					/*              LEFT  CD   CL  RIGHT */
					/* TOP    */ 	{"┌", "^", "T", "┐"},	
					/* HDRDATA */	{"1", "F", ":", "H"},
					/* HDRLINE*/	{"J", "-", "+", "M"},
					/* ROWDATA    */	{"l", "O", "!", "r"},
			    	/* ROWLINE*/	{"R", ".", "U", "V"},
					/* BOTTOM */	{"└", "v", "t", "┘"}
			    };

     		@Override
			public String getPattern(Row row, Column column) {
        		if(tablePattern[row.ordinal()] == null) {
        			return null;
        		} else {
        			return tablePattern[row.ordinal()][column.ordinal()];
        		}
        	}
    	}
		Style testStyle = new UniqueStyle();
        ConsoleTable table = new ConsoleTable().withStyle(testStyle);
        table.setHeaders("One", "Two", "Three");//optional - if not used then there will be no header and horizontal lines
        table.addRow(new java.math.BigDecimal("98.76"), "broccoli", "flexible");
        table.addRow(new java.math.BigDecimal("1.23"), "announcement", "reflection");
        table.addRow(new java.math.BigDecimal("1234.45"), null, java.time.LocalDateTime.parse("2020-12-24T14:15:16.987") );
        table.addRow( java.math.BigDecimal.ZERO, "pleasant", "wild");

        String expected = 
    		"┌^^^^^^^^^T^^^^^^^^^^^^^^T^^^^^^^^^^^^^^^^^^^^^^^^^┐"+"\n"+
    		"1     One :          Two :                   Three H"+"\n"+
    		"J---------+--------------+-------------------------M"+"\n"+
    		"l   98.76 !     broccoli !                flexible r"+"\n"+
    		"l    1.23 ! announcement !              reflection r"+"\n"+
    		"l 1234.45 !              ! 2020-12-24T14:15:16.987 r"+"\n"+
    		"l       0 !     pleasant !                    wild r"+"\n"+
    		"└vvvvvvvvvtvvvvvvvvvvvvvvtvvvvvvvvvvvvvvvvvvvvvvvvv┘"+"\n";

		assertLinesMatch( Arrays.asList(expected.split("\n")),
				Arrays.asList(table.toString().split("\n")), "four");
	}
	
	@Test
	void testWideStylePositions() {
		class UniqueWide implements Style {
			// a different multi character String in every position to check each is correct
		    String[][] tablePattern = new String[][] {
					/*              LEFT COLDATA  COLLINE  RIGHT  */
			    	/*                   UMN              */
					/* TOP    */ 	{"x┌", "^-", "wT", "z┐"},	
					/* HDRDATA */	{"x1", "HD", "w:", "zH"},
					/* HDRLINE*/	{"xJ", "-─", "w+", "zM"},
					/* ROWDATA    */	{"xl", "DA", "w!", "zr"},
			    	/* ROWLINE*/	{"xR", ".,", "wU", "zV"},
					/* BOTTOM */	{"x└", "v-", "wt", "z┘"} 
			    };

     		@Override
			public String getPattern(Row row, Column column) {
        		if(tablePattern[row.ordinal()] == null) {
        			return null;
        		} else {
        			return tablePattern[row.ordinal()][column.ordinal()];
        		}
        	}
    	}
		Style uniqueWide = new UniqueWide();
        ConsoleTable table = new ConsoleTable().withStyle(uniqueWide);
        table.setHeaders("One", "Two", "Three");//optional - if not used then there will be no header and horizontal lines
        table.addRow(new java.math.BigDecimal("98.76"), "broccoli", "flexible");
        table.addRow(new java.math.BigDecimal("1.23"), "announcement", "reflection");
        table.addRow(new java.math.BigDecimal("1234.45"), null, java.time.LocalDateTime.parse("2020-12-24T14:15:16.987") );
        table.addRow( java.math.BigDecimal.ZERO, "pleasant", "wild");

        String expected = 
    		"x┌^-^-^-^-^wT^-^-^-^-^-^-^-wT^-^-^-^-^-^-^-^-^-^-^-^-^z┐"+"\n"+
    		"x1     One w:          Two w:                   Three zH"+"\n"+
    		"xJ-─-─-─-─-w+-─-─-─-─-─-─-─w+-─-─-─-─-─-─-─-─-─-─-─-─-zM"+"\n"+
    		"xl   98.76 w!     broccoli w!                flexible zr"+"\n"+
    		"xl    1.23 w! announcement w!              reflection zr"+"\n"+
    		"xl 1234.45 w!              w! 2020-12-24T14:15:16.987 zr"+"\n"+
    		"xl       0 w!     pleasant w!                    wild zr"+"\n"+
    		"x└v-v-v-v-vwtv-v-v-v-v-v-v-wtv-v-v-v-v-v-v-v-v-v-v-v-vz┘"+"\n";

		assertLinesMatch( Arrays.asList(expected.split("\n")),
				Arrays.asList(table.toString().split("\n")), "four");
	}

	@Test
	void testCompactStyle() {
		/** a compact implementation of {@link Style} for single character lines and junctions
		 * 
		 * @author John Sinclair 2020-02-06
		 */
		class CompactLight implements Style {
		    String[] compactPattern = {
		  	    "┌─┬┐",	// T
		  		"│H││", // HD
		  		"├─┼┤", // HL
		  		"│D││", // RD
		  		"├─┼┤", // RL
		  		"└─┴┘"};// B

     		@Override
			public String getPattern(Row row, Column column) {
        		if(compactPattern[row.ordinal()] != null) {
        			return String.valueOf( compactPattern[ row.ordinal() ].charAt( column.ordinal() ) );
        		}
    			return null;
    			 
     		}
    	}
		
        ConsoleTable table = new ConsoleTable().withRowLines();
        table.setHeaders("One", "Two", "Three");
        table.addRow(new java.math.BigDecimal("98.76"), "broccoli", "flexible");
        table.addRow(new java.math.BigDecimal("1.23"), "announcement", "reflection");
        table.addRow(new java.math.BigDecimal("1234.45"), null, java.time.LocalDateTime.parse("2020-12-24T14:15:16.987") );
        table.addRow( java.math.BigDecimal.ZERO, "pleasant", "wild");

        String actual   = table.withStyle(new CompactLight()).toString();
        String expected = table.withStyle(Styles.LIGHT).toString();
        
		assertLinesMatch( Arrays.asList(expected.split("\n")),
				Arrays.asList(actual.split("\n")), "CompactLight Style");
	}
	
    @Test
	void testNull() {
	    ConsoleTable emptyTable = new ConsoleTable().withStyle(Styles.HEAVY_BORDER);

	    assertEquals(
	    "┏┓\n"+
	    "┗┛\n", emptyTable.toString());
    }

    @Test
    void testNullHeaders() {
	    ConsoleTable emptyTable = new ConsoleTable().withStyle(Styles.HEAVY);
	    emptyTable.setHeaders(null);
	    
	    assertEquals(
	    "┏┓"+"\n"+
	    "┗┛"+"\n", emptyTable.toString());
    }

    @Test
    void testNullHeadersCast() {
	    ConsoleTable emptyTable = new ConsoleTable().withStyle(Styles.LIGHT);
	    emptyTable.setHeaders((Object[])null);
	    
	    assertEquals(
	    "┌┐"+"\n"+
	    "└┘"+"\n", emptyTable.toString());
    }

    @Test
    void testTwoNullHeaders() {
	    ConsoleTable emptyTable = new ConsoleTable().withStyle(Styles.BASIC).withColumnPadding("");
	    emptyTable.setHeaders((Object[])null, (Object[])null);
	    
	    assertEquals(
	    "+++"+"\n"+
   	    "|||"+"\n"+
	    "+++"+"\n"+
	    "+++"+"\n", emptyTable.toString());
    }

    @Test
	void testEmptyHeaders() {
	    ConsoleTable emptyTable = new ConsoleTable().withStyle(Styles.HEAVY_BORDER);
	    emptyTable.setHeaders();
	    
	    assertEquals(
	    "┏┓"+"\n"+
	    "┗┛"+"\n", emptyTable.toString());
    }

    @Test
	void testNullRow() {
	    ConsoleTable emptyTable = new ConsoleTable().withStyle(Styles.HEAVY_BORDER).withColumnPadding("","");;
	    emptyTable.addRow();
	    
	    assertEquals(
	        "┏┓"+"\n"+
	        "┃┃"+"\n"+
	    	"┗┛"+"\n", emptyTable.toString());
    }

    @Test
	void testNullColumnRow() {
	    ConsoleTable table = new ConsoleTable().withStyle(Styles.BASIC).withColumnPadding("","");
	    table.addRow("a",null,"pq");
	    table.addRow("b","",  "rs");
	    
	    // if a column has no heading and no data then max width is 0 and it should have a width of 0
	    String expected =
		"+-++--+"+"\n"+
        "|a||pq|"+"\n"+
    	"|b||rs|"+"\n"+
        "+-++--+"+"\n"; 
	    assertLinesMatch( Arrays.asList(expected.split("\n")),
				Arrays.asList(table.toString().split("\n")), "if a column has no heading and no data then max width is 0 and it should have a width of 0");

    }

    @Test
	void testAddRowEmpty() {
	    ConsoleTable table = new ConsoleTable().withStyle(Styles.LIGHT).withColumnPadding("");
	    table.addRow();
	    
	    String expected =
	    	"┌┐"+"\n"+
	    	"││"+"\n"+
	    	"└┘"+"\n";
	    assertLinesMatch( Arrays.asList(expected.split("\n")),
				Arrays.asList(table.toString().split("\n")), "if a column has no heading and no data then max width is 0 and it should have a width of 0");

    }
    
    @Test
 	void testAddRowNullCastObjectArray() {
 	    ConsoleTable table = new ConsoleTable().withStyle(Styles.LIGHT).withColumnPadding("");
 	    table.addRow( (Object[])null );
 	    
 	    String expected =
		  "┌┐"+"\n"+
		  "││"+"\n"+
		  "└┘"+"\n";
 
 	    assertLinesMatch( Arrays.asList(expected.split("\n")),
 				Arrays.asList(table.toString().split("\n")), "if a column has no heading and no data then max width is 0 and it should have a width of 0");
    }

   @Test
 	void testAddRowNullCastObject() {
 	    ConsoleTable table = new ConsoleTable().withStyle(Styles.LIGHT).withColumnPadding("");
 	    table.addRow( (Object)null );
 	    
 	    String expected =
		  "┌┐"+"\n"+
		  "││"+"\n"+
		  "└┘"+"\n";
 
 	    assertLinesMatch( Arrays.asList(expected.split("\n")),
 				Arrays.asList(table.toString().split("\n")), "if a column has no heading and no data then max width is 0 and it should have a width of 0");
    }

    @Test
 	void testAddRowNullCastList() {
 	    ConsoleTable table = new ConsoleTable().withStyle(Styles.LIGHT).withColumnPadding("");
 	    table.addRow( (List<Object>)null );
 	    
 	    String expected =
		  "┌┐"+"\n"+
		  "││"+"\n"+
		  "└┘"+"\n";
 
 	    assertLinesMatch( Arrays.asList(expected.split("\n")),
 				Arrays.asList(table.toString().split("\n")), "if a column has no heading and no data then max width is 0 and it should have a width of 0");
    }
   
    @Test
 	void testAddRowEmptyString() {
 	    ConsoleTable table = new ConsoleTable().withStyle(Styles.LIGHT).withColumnPadding("");
 	    table.addRow( "" );
 	    
 	    String expected =
		  "┌┐"+"\n"+
		  "││"+"\n"+
		  "└┘"+"\n";
 
 	    assertLinesMatch( Arrays.asList(expected.split("\n")),
 				Arrays.asList(table.toString().split("\n")), "if a column has no heading and no data then max width is 0 and it should have a width of 0");
    }

    @Test
	void testOneHeader() {
	    ConsoleTable table = new ConsoleTable().withStyle(Styles.HEAVY_BORDER);
	    table.setHeaders("hdr");
	    
	    assertEquals(
	        "┏━━━━━┓"+"\n"+
	        "┃ hdr ┃"+"\n"+
	        "┣━━━━━┫"+"\n"+
	    	"┗━━━━━┛"+"\n", table.toString());
    }

    @Test
	void testOneByOne() {
	    ConsoleTable table = new ConsoleTable().withStyle(Styles.HEAVY_BORDER);
	    table.addRow("1x1");

	    assertEquals(
	    "┏━━━━━┓"+"\n"+
	    "┃ 1x1 ┃"+"\n"+
	    "┗━━━━━┛"+"\n", table.toString());
    }

    @Test
	void testTwoByTwo() {
	    ConsoleTable twoByTwo = new ConsoleTable().withStyle(Styles.HEAVY_BORDER).withColumnPadding(",.",".,");
	    twoByTwo.addRow("1a", "1b");
	    twoByTwo.addRow("2a", "2b");

	    assertEquals(
	    "┏━━━━━━┯━━━━━━┓"+"\n"+
	    "┃,.1a.,│,.1b.,┃"+"\n"+
	    "┃,.2a.,│,.2b.,┃"+"\n"+
	    "┗━━━━━━┷━━━━━━┛"+"\n",
	    twoByTwo.toString(), "TwoByTwo with double padding");

	    
	    // MyAssert.assertLinesEqual(
	    String expected = 
			    "┏━━━━━━┯━━━━━━┓"+"\n"+
			    "┃,.1a.,│,.1b.,┃"+"\n"+
			    "┃,.2a.,│,.2b.,┃"+"\n"+
			    "┗━━━━━━┷━━━━━━┛"+"\n";

		assertLinesMatch( Arrays.asList(expected.split("\n")),
				Arrays.asList(twoByTwo.toString().split("\n")), "TwoByTwo with double padding");

	}
	
    @Test
	void testThreeByThree() {
    	ConsoleTable threeByThree = new ConsoleTable().withStyle(Styles.HEAVY_BORDER);
        threeByThree.setHeaders("a1");
        threeByThree.addRow("a2","b2");
        threeByThree.addRow("a3", "b3", "c3");

	    String expected = 
	    		"┏━━━━┯━━━━┯━━━━┓"+"\n"+
	    		"┃ a1 │    │    ┃"+"\n"+
	    		"┣━━━━┿━━━━┿━━━━┫"+"\n"+
	    		"┃ a2 │ b2 │    ┃"+"\n"+
	    		"┃ a3 │ b3 │ c3 ┃"+"\n"+
	    		"┗━━━━┷━━━━┷━━━━┛"+"\n";

		assertLinesMatch( Arrays.asList(expected.split("\n")),
				Arrays.asList(threeByThree.toString().split("\n")), "test three by three with empty headers and cells");

	}

	@Test
	void testFourByFourAndColumnFormat() {
		ConsoleTable four = new ConsoleTable().withStyle(Styles.HEAVY_BORDER);
	    four.setHeaders("a1", new ColumnFormat("a2", Aligned.LEFT), "a3");
	    four.addRow("a2", "b2");
	    four.addRow();
	    four.addRow("widea3", "wideb3", "widec3");

	    String expected = 
	    		"┏━━━━━━━━┯━━━━━━━━┯━━━━━━━━┓"+"\n"+
	    		"┃     a1 │ a2     │     a3 ┃"+"\n"+
	    		"┣━━━━━━━━┿━━━━━━━━┿━━━━━━━━┫"+"\n"+
	    		"┃     a2 │ b2     │        ┃"+"\n"+
	    		"┃        │        │        ┃"+"\n"+
	    		"┃ widea3 │ wideb3 │ widec3 ┃"+"\n"+
	    		"┗━━━━━━━━┷━━━━━━━━┷━━━━━━━━┛"+"\n";
		assertLinesMatch( Arrays.asList(expected.split("\n")),
				Arrays.asList(four.toString().split("\n")), "test four by four with empty rows and ColumnFormat");
//		assertArraysEqual( expected.split("\n"),
//				four.toString().split("\n"), "four");
	}
	
	
	@Test
	void testCustomStyle() {

		class DoubleStyle implements Style {
		    private String[][] tablePattern = new String[][] {
				/*              LEFT   COLDATA COLLINE RIGHT  */
		    	/*                     UMN              */
				/* TOP    */ 	{"x",  "=",   "x", "x" },	
				/* HDRDATA */	{":", "Hdr",  ":", ":" },
				/* HDRLINE*/	{"x",  "=",   "x", "x" },
				/* ROWDATA    */	{":", "Data", ":", ":" },
		    	/* ROWLINE*/	{":",  "-",   ".", ":" },
				/* BOTTOM */	{"x",  "=",   "x", "x" } };
	
		    public String getPattern(Row row, Column column) {
        		if(tablePattern[row.ordinal()] == null) {
        			return null;
        		} else {
        			return tablePattern[row.ordinal()][column.ordinal()];
        		}
        	}

		    public String[][] getTable() {
		    	return tablePattern;
		    }
		}
	
		DoubleStyle doubleStyle = new DoubleStyle();
	    ConsoleTable six = new ConsoleTable((doubleStyle).getTable()).withStyle(doubleStyle);
	    six.setHeaders(new ColumnFormat("Left",Aligned.CENTRE),new ColumnFormat("Cell",Aligned.CENTRE),new ColumnFormat("Line",Aligned.CENTRE),new ColumnFormat("Right",Aligned.CENTRE));

	    String expected = 
	    	"x======x======x======x=======x"+"\n"+
    		": Left : Cell : Line : Right :"+"\n"+
    		"x======x======x======x=======x"+"\n"+
    		":   x  :   =  :   x  :   x   :"+"\n"+
    		":   :  :  Hdr :   :  :   :   :"+"\n"+
    		":   x  :   =  :   x  :   x   :"+"\n"+
    		":   :  : Data :   :  :   :   :"+"\n"+
    		":   :  :   -  :   .  :   :   :"+"\n"+
    		":   x  :   =  :   x  :   x   :"+"\n"+
    		"x======x======x======x=======x"+"\n";
		assertLinesMatch( Arrays.asList(expected.split("\n")),
				Arrays.asList(six.toString().split("\n")), "test custom style with pattern table");
		
		ConsoleTable seven = new ConsoleTable().withStyle(doubleStyle).setHeaders("-Date","'Temperature");
	    seven.addRow(LocalDate.parse("2021-12-25"), "21°C");
	    
	    String expectedOutput = 
	    		"x============x=============x"+"\n"+
	    		": Date       : Temperature :"+"\n"+
	    		"x============x=============x"+"\n"+
	    		": 2021-12-25 :     21°C    :"+"\n"+
	    		"x============x=============x"+"\n";
		assertLinesMatch( Arrays.asList(expectedOutput.split("\n")),
				Arrays.asList(seven.toString().split("\n")), "test custom style with real world data");  
		}
	
		@Test
		void testAlignments() {
	        ConsoleTable alignmentsTable = new ConsoleTable().withStyle(Styles.BASIC);
	        alignmentsTable.setHeaders("-Test", new ColumnFormat("Left",Aligned.LEFT), "'Centre", new ColumnFormat("Right",Aligned.RIGHT));
	        alignmentsTable.addRow("Short", "a", "1", "x");
	        alignmentsTable.addRow("Medium", "b2", "22", "yy");
	        alignmentsTable.addRow("Medium", "c23", "223", "yy3");
//	        alignmentsTable.addRow("Medium", "c234", "2234", "yy34");
	        alignmentsTable.addRow("Longest", "d23456", "e2345678", "z12345");

	        String expected =
	        "+---------+--------+----------+--------+"+"\n"+
	        "| Test    | Left   |  Centre  |  Right |"+"\n"+
	        "+---------+--------+----------+--------+"+"\n"+
	        "| Short   | a      |     1    |      x |"+"\n"+
	        "| Medium  | b2     |    22    |     yy |"+"\n"+
	        "| Medium  | c23    |    223   |    yy3 |"+"\n"+
	        "| Longest | d23456 | e2345678 | z12345 |"+"\n"+
	        "+---------+--------+----------+--------+"+"\n";
			assertLinesMatch( Arrays.asList(expected.split("\n")),
					Arrays.asList(alignmentsTable.toString().split("\n")), "test Alignments");

		}
		
		@Test
		void testAlignmentOnly() {
	        ConsoleTable alignmentsTable = new ConsoleTable().withStyle(Styles.BASIC);
	        alignmentsTable.setHeaders("'", "-Left", "'Centre", "Right");
	        alignmentsTable.addRow("Low", "a", "1", "x");
	        alignmentsTable.addRow("Large", "b2", "22", "yy");
	        alignmentsTable.addRow("Extreme", "d23456", "e2345678", "z12345");

	        String expected =
	        "+---------+--------+----------+--------+"+"\n"+
	        "|         | Left   |  Centre  |  Right |"+"\n"+
	        "+---------+--------+----------+--------+"+"\n"+
	        "|   Low   | a      |     1    |      x |"+"\n"+
	        "|  Large  | b2     |    22    |     yy |"+"\n"+
	        "| Extreme | d23456 | e2345678 | z12345 |"+"\n"+
	        "+---------+--------+----------+--------+"+"\n";
			assertLinesMatch( Arrays.asList(expected.split("\n")),
					Arrays.asList(alignmentsTable.toString().split("\n")), "test Alignments");

		}
		
		
		@Test
		void testArrayHeaderData() {
	        String[] petHeaders = { "-Pet", "Age", "'Sex" };
	        Object[][] petData = {
	        		{ "Cat", 10, "F" },
	        		{ "Dog",  5, "M"}
	        };
		    ConsoleTable petShopTable = new ConsoleTable(petHeaders, petData).withStyle(Styles.BASIC);

	        String expected =
	        "+-----+-----+-----+"+"\n"+
	        "| Pet | Age | Sex |"+"\n"+
	        "+-----+-----+-----+"+"\n"+
	        "| Cat |  10 |  F  |"+"\n"+
	        "| Dog |   5 |  M  |"+"\n"+
	        "+-----+-----+-----+"+"\n";
			assertLinesMatch( Arrays.asList(expected.split("\n")),
					Arrays.asList(petShopTable.toString().split("\n")), "test new(Array, Array)");

		}
	
		@Test
		void testPaddingInStyles() {
		    ConsoleTable zooTable = new ConsoleTable(
		    		new String[] { "-Animal", "Cost", "'Sex" },
		    		new Object[][] {
			        		{ "Cat", new BigDecimal("10.00"), "F" },
			        		{ "Zebra", new BigDecimal("5678.99"), "M"}
			        }).withStyle(Styles.SQL);
		    
	        String expected =
        		"Animal    Cost Sex"+"\n"+
        		"------ ------- ---"+"\n"+
        		"Cat      10.00  F "+"\n"+
        		"Zebra  5678.99  M "+"\n";

			assertLinesMatch( Arrays.asList(expected.split("\n")),
					Arrays.asList(zooTable.toString().split("\n")), "test new(Array, Array)");

		}
	
		@Test
		void testSimplestStyle() {

			class Simple implements Style {

				public String getPattern(Row row, Column column) {
					if(row == Row.HDRDATA || row == Row.ROWDATA ) {
						if( column == Column.COLLINE )
							return " ";
						else
							return "";
					}
					else
						return null;
				}
				
				public String getPadding(Column column) {
					return "";
				}
			}
			
			ConsoleTable simple = new ConsoleTable().withStyle(new Simple());
			simple.setHeaders("Pet","Cost");
			simple.addRow("Rusty", 100);
			simple.addRow("Red", 42);
			
	        String expected =
	        		"  Pet Cost"+"\n"+
	        		"Rusty  100"+"\n"+
	        		"  Red   42"+"\n";

			assertLinesMatch( Arrays.asList(expected.split("\n")),
					Arrays.asList(simple.toString().split("\n")), "test super simple Style");
			
			
			
		}

		@Test
		void testSimplerStyle() {

			class Simple implements Style {
				public String getPattern(Row row, Column column) {
					return ".";
				}
			}
			
			ConsoleTable simple = new ConsoleTable().withStyle(new Simple());
			simple.setHeaders("Pet","Cost");
			simple.addRow("Rusty", 100);
			simple.addRow("Red", 42);
			
			System.out.print(simple);
			// TODO assert
			
			
		}
		
		@Test
		void testListHeaderData() {
	        List<String> petHeaders = Arrays.asList( "-Pet", "Age", "'Sex" );
	        List<List<Object>> petData = Arrays.asList(
	        		Arrays.asList( "Cat", 10, "F" ),
	        		Arrays.asList( "Dog",  5, "M" )
	        );
		    ConsoleTable petShopTable = new ConsoleTable(petHeaders, petData).withStyle(Styles.BASIC);

	        String expected =
	        "+-----+-----+-----+"+"\n"+
	        "| Pet | Age | Sex |"+"\n"+
	        "+-----+-----+-----+"+"\n"+
	        "| Cat |  10 |  F  |"+"\n"+
	        "| Dog |   5 |  M  |"+"\n"+
	        "+-----+-----+-----+"+"\n";
			assertLinesMatch( Arrays.asList(expected.split("\n")),
					Arrays.asList(petShopTable.toString().split("\n")), "test new(Array, Array)");

	        ConsoleTable table = new ConsoleTable();
	        table.setHeaders("Header", "Header");
	        table.addRow("Cell","b2");
	        table.addRow("a3", "b3");
	        System.out.print(table);
		}

	}


