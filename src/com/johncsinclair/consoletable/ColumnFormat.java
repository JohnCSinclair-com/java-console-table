package com.johncsinclair.consoletable;

/**
 * Format settings for a column in a {@link ConsoleTable}. Use a <code>ColumnFormat</code> in {@link ConsoleTable#setHeaders}.
 * 
 * @author Copyright (c) John C Sinclair 2021
 *
 */
public class ColumnFormat {

	/**
	 * A {@link ConsoleTable} column can be aligned LEFT, CENTRE or RIGHT.
	 */
	public enum Aligned { LEFT, CENTRE, RIGHT };
	
	private String columnHeading;
	private Aligned alignment = Aligned.RIGHT;

	/**
	 * @param columnHeading The heading for the column. By default the column will be right aligned, 
	 * if columnHeading starts with <code>-</code> the column will be left aligned, 
	 * if columnHeading starts with <code>'</code> the column will be centred.
	 */
	public ColumnFormat(String columnHeading) {
		final char firstChar = columnHeading.charAt(0);
		if(firstChar == '-' || firstChar == '\'') {
			columnHeading = columnHeading.substring(1);
			this.alignment = firstChar == '-' ? Aligned.LEFT : Aligned.CENTRE;
		}
		this.columnHeading = columnHeading;
	}

	/**
	 * @param columnHeading The heading for the column.
	 * @param alignment The alignment for the column.
	 */
	public ColumnFormat(String columnHeading, Aligned alignment) {
		this(columnHeading);
		this.alignment = alignment;
	}

	public Aligned getAlignment() {
		return alignment;
	}

	@Override
	public String toString() {
		return columnHeading;
	}

}
