package com.johncsinclair.consoletable;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;


/**
 * Pre-configured {@link Style} for {@link ConsoleTable}.
 *
 * The Style of the table includes the outer border lines, the lines between the rows and the columns
 *  
 * Example
 * <pre>
 * Pet Age
 * --- ---
 * Cat   5
 * Dog  10
 * </pre>
 * 
 * @author Copyright (c) John C Sinclair 2021
 */

public enum Styles implements Style { 

	/**
	 * A plain style with a header line.
	 * <p>
	 * Example
	 * <pre>
     * Pet Age
     * --- ---
     * Cat   5
     * Dog  10
     * </pre>
     */
	PLAIN (
			new String[][] { 
			 	null,	
				{"" , "H", " ", "" },
				{"" , "-", " ", "" },
				{"" , "1", " ", "" },
				null,
				null }
			),
	/**
	 * A minimal style with a dashed header line
	 * <p>
	 * Example
	 * <pre>
     * Pet Age
     * --- ---
     * Cat   5
     * Dog  10
     * </pre>
     */
	SQL (
			new String[][] { 
			 	null,	
				{"" , "H", " ", "" },
				{"" , "-", " ", "" },
				{"" , "1", " ", "" },
				null,
				null,
				{"" ,null,null, "" } }
			),
  /**
    * A minimal table with no borders and no cell padding
	 * <p>
	 * Example
	 * <pre>
     * Pet Age
     * Cat   5
     * Dog  10
     * </pre>
    */
   MINIMAL (
    		new String[][] {
    	 	null,	
    		{"" , "H", " ", "" },
    		null,
    		{"" , "1", " ", "" },
    		null,
    		null,
			{"" ,null,null, "" } }
    ),

   /**
    * A minimal table with no vertical lines
    */
   NO_LINES (
    		new String[][] {
    	/* TOP    */ 	null,	
    	/* HDRDATA*/	{"" , "H", "",  "" },
    	/* HDRLINE*/	null,
    	/* ROWDATA*/	{"" , "1", "",  "" },
    	/* ROWLINE*/	null,
    	/* BOTTOM */	null }
    ),
 
   /**
    * A simple border.
    */
   BORDER (
    		new String[][] { 
    			      /* LEFT COLDATA  COLLINE  RIGHT  */
    	/* TOP    */ 	{"┌─", "─", "─", "─┐"},	
    	/* HDRDATA*/	{"│ ", "H", " ", " │"},
    	/* HDRLINE*/	{"│ ", "─", " ", " │"},
    	/* ROWDATA*/	{"│ ", "1", " ", " │"},
    	/* ROWLINE*/	{"│ ", " ", " ", " │"},
    	/* BOTTOM */	{"└─", "─", "─", "─┘"},
		/* PADDING*/	{"" ,null,null, "" } }
    ),

   /**
    * For a paper in a scientific research journal. No vertical lines.
    * 
    * @see <a href="https://apastyle.apa.org/style-grammar-guidelines/tables-figures/tables">APA Style - Table Setup - American Psychological Association</a>
    */
   SCIENTIFIC (
    		new String[][] { 
    			      /* LEFT COLDATA  COLLINE  RIGHT  */
    	/* TOP    */ 	{"─", "─", "─", "─"},	
    	/* HDRDATA*/	{" ", "H", " ", " "},
    	/* HDRLINE*/	{"─", "─", "─", "─"},
    	/* ROWDATA*/	{" ", "D", " ", " "},
    	/* ROWLINE*/	{" ", " ", " ", " "},
    	/* BOTTOM */	{"─", "─", "─", "─"} }
    ),
   /**
	 * javadoc testing2
	 */
	BASIC (
	    	new String[][] {
	    			      /* LEFT COLDATA  COLLINE  RIGHT  */
	    	/* TOP    */ 	{"+", "-", "+", "+"},	
	    	/* HDRDATA*/	{"|", "H", "|", "|"},
	    	/* HDRLINE*/	{"+", "-", "+", "+"},
	    	/* ROWDATA*/	{"|", "1", "|", "|"},
	    	/* ROWLINE*/	{"+", "-", "+", "+"},
	    	/* BOTTOM */	{"+", "-", "+", "+"} }
	    ),

	/**
	 * A simple table.
	 * <p>
	 * Example
	 * <pre>
	 * .─┬─.	
     * │H│H│
     * ├─+─┤
     * │1│2│
     * ├─+─┤
     * │3│4│
     * .─┴─.
     *	</pre>
     */
	SIMPLE (
    		new String[][] {
    			      /* LEFT COLDATA  COLLINE  RIGHT  */
    	/* TOP    */ 	{"·", "-", "-", "·"},	
    	/* HDRDATA*/	{"|", "H", "|", "|"},
    	/* HDRLINE*/	{"|", "-", "+", "|"},
    	/* ROWDATA*/	{"|", "1", "|", "|"},
    	/* ROWLINE*/	{"|", "-", "+", "|"},
    	/* BOTTOM */	{"·", "-", "-", "·"} }
    ),

//	   BULLETS (
//	    		new String[][] {
//	    			      /* LEFT COLDATA  COLLINE  RIGHT  */
//	    	/* TOP    */ 	{"•", " • ", "•", "•"},	
//	    	/* HDRDATA*/	{"•", "H",   "•", "•"},
//	    	/* HDRLINE*/	{"•", " • ", "•", "•"},
//	    	/* ROWDATA*/	{"•", "1",   "•", "•"},
//	    	/* ROWLINE*/	{"•", " • ", "•", "•"},
//	    	/* BOTTOM */	{"•", " • ", "•", "•"} }
//	    ),
	
   DOTS (
	    	new String[][] {
	    			      /* LEFT COLDATA  COLLINE  RIGHT  */
	    	/* TOP    */ 	{".", ".", ".", "."},	
	    	/* HDRDATA*/	{":", "H", ":", ":"},
	    	/* HDRLINE*/	{":", ".", ":", ":"},
	    	/* ROWDATA*/	{":", "1", ":", ":"},
	    	/* ROWLINE*/	{":", ".", ":", ":"},
	    	/* BOTTOM */	{":", ".", ":", ":"} }
	    ),

// U+00B7 · MIDDLE DOT · is not a full stop .
   MIDDLE_DOTS (
    	new String[][] {
    			      /* LEFT COLDATA  COLLINE  RIGHT  */
    	/* TOP    */ 	{"·", "·", "·", "·"},	
    	/* HDRDATA*/	{":", "H", ":", ":"},
    	/* HDRLINE*/	{":", "·", ":", ":"},
    	/* ROWDATA*/	{":", "1", ":", ":"},
    	/* ROWLINE*/	{":", "·", ":", ":"},
    	/* BOTTOM */	{"·", "·", "·", "·"} }
    ),

   DASHES (
    		new String[][] {
    			      /* LEFT COLDATA  COLLINE  RIGHT  */
    	/* TOP    */ 	{" ", "_", " ", " "},	
    	/* HDRDATA*/	{"|", "H", "|", "|"},
    	/* HDRLINE*/	{"|", "_", "|", "|"},
    	/* ROWDATA*/	{"|", "1", "|", "|"},
    	/* ROWLINE*/	{"|", "_", "|", "|"},
    	/* BOTTOM */	{"|", "_", "|", "|"} }
    ),

   STARS (
    		new String[][] {
    			      /* LEFT COLDATA  COLLINE  RIGHT  */
    	/* TOP    */ 	{"*", ".", "*", "*"},	
    	/* HDRDATA*/	{":", "H", ":", ":"},
    	/* HDRLINE*/	{":", ".", "*", ":"},
    	/* ROWDATA*/	{":", "1", ":", ":"},
    	/* ROWLINE*/	{":", ".", "*", ":"},
    	/* BOTTOM */	{"*", ".", "*", "*"} }
    ),
   
	/**
	 * A light continuous border using Unicode box drawing characters.
	 * <pre>
	 * 	┌─┬┐	
     *	│H││
     *	├─┼┤
     *	│D││
     *	├─┼┤
     *	└─┴┘
     *	</pre>
	 */
   LIGHT (
    		new String[][] { 
    			      /* LEFT COLDATA  COLLINE  RIGHT  */
    	/* TOP    */ 	{"┌", "─", "┬", "┐"},	
    	/* HDRDATA*/	{"│", "H", "│", "│"},
    	/* HDRLINE*/	{"├", "─", "┼", "┤"},
    	/* ROWDATA*/	{"│", "1", "│", "│"},
    	/* ROWLINE*/	{"├", "─", "┼", "┤"},
    	/* BOTTOM */	{"└", "─", "┴", "┘"} }
    ),

    DASHED ( new String[][] {
		/*              LEFT COLDATA  COLLINE  RIGHT  */
		/* TOP    */ 	{"┌", "╌", "┬", "┐"},	
		/* HDRDATA*/	{"┆", "H", "┆", "┆"},
		/* HDRLINE*/	{"┆", "╌", "┆", "┆"},
		/* ROWDATA*/	{"┆", "1", "┆", "┆"},
    	/* ROWLINE*/	{"┆", "╌", "┆", "┆"},
		/* BOTTOM */	{"└", "╌", "┴", "┘"} }
    ),
    
    ROUNDED ( new String[][] {
                     /* LEFT COLDATA  COLLINE  RIGHT  */
		/* TOP    */ 	{"╭", "─", "┬", "╮"},	
		/* HDRDATA*/	{"│", "H", "│", "│"},
		/* HDRLINE*/	{"├", "─", "┼", "┤"},
		/* ROWDATA*/	{"│", "1", "│", "│"},
    	/* ROWLINE*/	{"├", "─", "┼", "┤"},
		/* BOTTOM */	{"╰", "─", "┴", "╯"} }
	),

    HEAVY ( new String[][] {
		/*              LEFT COLDATA  COLLINE  RIGHT  */
		/* TOP    */ 	{"┏", "━", "┳", "┓"},	
		/* HDRDATA*/	{"┃", "H", "┃", "┃"},
		/* HDRLINE*/	{"┣", "━", "╋", "┫"},
		/* ROWDATA*/	{"┃", "1", "┃", "┃"},
    	/* ROWLINE*/	{"┣", "━", "╋", "┫"},
		/* BOTTOM */	{"┗", "━", "┻", "┛"} }
    ),
    
    HEAVY_BORDER ( new String[][] {
		/*              LEFT COLDATA  COLLINE  RIGHT  */
		/* TOP    */ 	{"┏", "━", "┯", "┓"},	
		/* HDRDATA*/	{"┃", "H", "│", "┃"},
		/* HDRLINE*/	{"┣", "━", "┿", "┫"},
		/* ROWDATA*/	{"┃", "1", "│", "┃"},
    	/* ROWLINE*/	{"┠", "─", "┼", "┨"},
		/* BOTTOM */	{"┗", "━", "┷", "┛"} }
    ),
    
    LIGHT_HEADER ( new String[][] {
		/*              LEFT COLDATA  COLLINE  RIGHT  */
		/* TOP    */ 	{"┏", "━", "┯", "┓"},	
		/* HDRDATA*/	{"┃", "H", "│", "┃"},
		/* HDRLINE*/	{"┠", "─", "┼", "┨"},
		/* ROWDATA*/	{"┃", "1", "│", "┃"},
    	/* ROWLINE*/	{"┠", "─", "┼", "┨"},
		/* BOTTOM */	{"┗", "━", "┷", "┛"} }
    ),
    
    DOUBLE_BORDER ( new String[][] {
		/*              LEFT COLDATA COLLINE RIGHT  */
		/* TOP    */ 	{"╔", "═", "╤", "╗"},	
		/* HDRDATA*/	{"║", "H", "│", "║"},
		/* HDRLINE*/	{"╠", "═", "╪", "╣"},
		/* ROWDATA*/	{"║", "1", "│", "║"},
    	/* ROWLINE*/	{"╟", "─", "┼", "╢"},
		/* BOTTOM */	{"╚", "═", "╧", "╝"} }
    ),
    
    DOUBLED ( new String[][] { 
    			      /* LEFT COLDATA  COLLINE  RIGHT  */
    	/* TOP    */ 	{"┌", "─", "┐┌", "┐"},	
    	/* HDRDATA*/	{"│", "H", "││", "│"},
    	/* HDRLINE*/	{"├", "─", "┤├", "┤"},
    	/* ROWDATA*/	{"│", "1", "││", "│"},
    	/* ROWLINE*/	{"├", "─", "┤├", "┤"},
    	/* BOTTOM */	{"└", "─", "┘└", "┘"} }
    ),
    
    COMPACT ( new String[]{
		  	    "┌─┬┐",	// T
		  		"│H││", // HD
		  		"├─┼┤", // HL
		  		"│D││", // RD
		  		"├─┼┤", // RL
		  		"└─┴┘"} // B
    );


    private String[][] tablePattern;
	   
    private Styles (String[][] tablePattern){
    	this.tablePattern = tablePattern;
    }
    	
    private Styles (String[] compactPattern){
    	String[][] pattern = new String[Row.values().length][Column.values().length];
    	for(Row row : Row.values()) {
    		// TODO - handle null row
    		for(Column column : Column.values()) {
    			pattern[row.ordinal()][column.ordinal()] = String.valueOf( compactPattern[row.ordinal()].charAt(column.ordinal()) );
    		}
    	}
    	this.tablePattern = pattern;
    }

	@Override
	public String getPattern(Row row, Column column) {
		
		// TODO if row.ordinal() > tablePattern[].length throw a helpful exception for a Style developer. ( or col.ordinal()  )
		
		if(tablePattern[row.ordinal()] == null) {
			return null;
		} else {
			return tablePattern[row.ordinal()][column.ordinal()];
		}
	}

	/**
	 * return the <code>String</code> to display for either LEFT or RIGHT column padding for this Style
	 * 
	 * @param row
	 * @param column
	 * @return null if the <code>row</code> is not displayed, otherwise the string to display at the given position of the ConsoleTable style 
	 */
	@Override
	public String getPadding(Column column)
	{
		if(column == Column.LEFT || column == Column.RIGHT) {
			if( tablePattern.length > Row.BOTTOM.ordinal()+1) {
    			return tablePattern[Row.BOTTOM.ordinal()+1][column.ordinal()];
			}
			else {
				return " ";
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}




    public static void main(String[] args) {

	    ConsoleTable petTable = new ConsoleTable();
        petTable.setHeaders("Pet", "Age", "'Sex");
        petTable.addRow("Ant",  1, "M");
        petTable.addRow("Bat",  4, "F");
        petTable.addRow("Cat", 10, "F");
        petTable.addRow("Dog",  5, "M");
        
	    System.out.println("Example of all Styles");
	    petTable.withVerticalLines(true);
	    petTable.withColumnPadding("", "");
        for(Styles sample : Styles.values()) {
            System.out.printf("withStyle(Styles.%s)\n",sample.toString());
            System.out.print(petTable.withStyle(sample));
            System.out.println();
        }



        
        for(Style sample : Styles.values()) {
        	ConsoleTable sampleDump = dumpStyle(sample);
            sampleDump.withStyle(sample);
            System.out.format("withStyle(Styles.%s)\n",sample.toString());
            System.out.print(sampleDump);
            System.out.println();
        }
        
        
	    ConsoleTable tinySample = new ConsoleTable().withRowLines(false);
	    tinySample.setHeaders("H", "H");
	    tinySample.addRow(1, "a");
	    tinySample.addRow(2, "b");

        
	    System.out.println("Example of all Styles");
        for(Styles sample : Styles.values()) {
            System.out.printf("withStyle(Styles.%s)\n",sample.toString());
            System.out.print(tinySample.withStyle(sample));
            System.out.println();
        }
        
        for(Styles sample : Styles.values()) {
            System.out.printf("withStyle(Styles.%s)\n",sample.toString());
	        ConsoleTable dayTable = new ConsoleTable();
	        dayTable.setHeaders("Enum","English", "French", "German");
			for( DayOfWeek dayOfWeek : DayOfWeek.values()) {
				dayTable.addRow( dayOfWeek, dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH), 
						dayOfWeek.getDisplayName(TextStyle.FULL, Locale.FRENCH), dayOfWeek.getDisplayName(TextStyle.FULL, Locale.GERMAN),
						(dayOfWeek.getValue()-1)*20);
			}
			System.out.print(dayTable.withStyle(sample));
            System.out.println();
        }

    }

	/**
	 * return a {@link ConsoleTable} illustrating the rows and columns that make up the given {@link Style}.
	 * 
	 * @param dumpStyle
	 * @return
	 */
	public static ConsoleTable dumpStyle(Style dumpStyle) {
		
		String padding = null;
		String leftPadding = dumpStyle.getPadding(Column.LEFT);
		String rightPadding = dumpStyle.getPadding(Column.RIGHT);
		if( ! ( leftPadding.equals(" ")	&& rightPadding.equals(" ") ) ) {
			padding = "padding('"+leftPadding+"','"+rightPadding+"')";
			System.out.println(padding);
		}
		
		String[] styleHeaders = new String[Column.values().length+1];
		String[][] styleData = new String[Row.values().length][Column.values().length+1];

		for( Column column : Column.values() ) {
			styleHeaders[column.ordinal()+1] = "'"+column.name();
		}
		for( Row row : Row.values() ) {
			styleData[row.ordinal()][0] = row.name();
			for( Column column : Column.values() ) {
				String pattern = dumpStyle.getPattern(row, column);
				if(pattern == null) {
					if(column == Column.LEFT) {
						pattern = "null";
					}
					else {
						pattern = "";
					}
				}
				else if( pattern.equals("")) {
					pattern = "''";
				}
				else if( !pattern.trim().equals(pattern)) {
					pattern = "'" + pattern +"'";
				}
				styleData[row.ordinal()][column.ordinal()+1] = pattern;
			}
		}
		return new ConsoleTable(styleHeaders, styleData);
	}

}