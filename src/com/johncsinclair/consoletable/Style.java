package com.johncsinclair.consoletable;

/**
 * Describe the <code>String</code>s that style the appearance of the lines and junctions of a {@link ConsoleTable}.
 * <pre>
 *               LEFT   COLLINE   RIGHT 
 *                  COLDATA  COLDATA
 *    TOP         +--------+--------+   
 *       HDRDATA  | Header | Header |   
 *    HDRLINE     +--------+--------+   
 *       ROWDATA  |  Data  |  Data  |   
 *    ROWLINE     +--------+--------+   
 *       ROWDATA  |  Data  |  Data  |   
 *    BOTTOM      +--------+--------+   
 * </pre>
 * 
 * @author Copyright (c) John C Sinclair 2021
 *
 */
public interface Style {

	/**
	 * The types of rows that style the appearance of a {@link ConsoleTable}
	 */
	public enum Row { TOP, HDRDATA, HDRLINE, ROWDATA, ROWLINE, BOTTOM };	//  T HD HL RD RL B

	/**
	 * The types of columns that style the appearance of a {@link ConsoleTable}
	 */
	public enum Column { LEFT, COLDATA, COLLINE, RIGHT };	// L CD CL R

	/**
	 * return the <code>String</code> to display in the position in the table at the given <code>row</code> and <code>column</code>
	 * 
	 * @param row
	 * @param column
	 * @return the string to display at the given position of the ConsoleTable style, or null if the <code>row</code> is not displayed for the style.  
	 */
	public String getPattern(Row row, Column column);
	
	/**
	 * return the <code>String</code> to display for either LEFT or RIGHT data column padding for this Style, by default a space.
	 * 
	 * @param column
	 * @return the string to display at the given side of each column for padding 
	 */
	public default String getPadding(Column column)
	{
		return " ";
	}

}
