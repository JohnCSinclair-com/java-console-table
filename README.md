# java-console-table a ConsoleTable for Java

Copyright (c) John C Sinclair 2021

![license MIT](https://img.shields.io/github/license/JohnCSinclair-com/java-console-table)

A `ConsoleTable` formats a table of rows and columns into a 
single `String`, which can be printed on the console with a monospaced font.
 
Example code:

```java
ConsoleTable table = new ConsoleTable();
table.setHeaders("-City", "'Lat", "Longitude");
table.addRow("Paris", 48.86,2.34);
table.addRow("New York", 40.74, -73.99);
table.addRow("London", 51.52, -0.13);
table.addRow("Brisbane", -27.47, 153.03);
System.out.print(table);

System.out.print(table.withStyle(Styles.BASIC));
```

Console output:

```
┌──────────┬────────┬───────────┐
│ City     │   Lat  │ Longitude │
├──────────┼────────┼───────────┤
│ Paris    │  48.86 │      2.34 │
│ New York │  40.74 │    -73.99 │
│ London   │  51.52 │     -0.13 │
│ Brisbane │ -27.47 │    153.03 │
└──────────┴────────┴───────────┘
+----------+--------+-----------+
| City     |   Lat  | Longitude |
+----------+--------+-----------+
| Paris    |  48.86 |      2.34 |
| New York |  40.74 |    -73.99 |
| London   |  51.52 |     -0.13 |
| Brisbane | -27.47 |    153.03 |
+----------+--------+-----------+
```


## Features

ConsoleTable makes it easy to build stylish <b>tables</b>, which can be printed into a single string with all formats preserved.<p>
The core features are:
- Never have to work out the width of a column, `ConsoleTable` does it for you.
- Be forgiving of input data and handle nulls and missing columns.
- Display an optional header row.
- Display borders and lines with a predefined or user defined `Style`
- Uses the `toString()` method to print any Object
- Accepts table data as `Object[][]` or `List<Object[]>` or `List<List<Object>>`.
- Uses fluent style method chaining. `Table.of(headers,data).withStyle(Styles.SQL)`
- A column can be `Aligned` either `LEFT`, `CENTRE` or `RIGHT`.
- `ColumnFormat` defaults to `RIGHT`, you can set a column header to make it `LEFT` or `CENTRE`
- You can add a string Header, start with a `-` and it will be aligned `LEFT`, with a `'` it will be `CENTRE`



#### Convert `Object[][]` to `ConsoleTable`
If you already have ```Object[][] data```, you can print it on the console as a formatted table instantly:

```java
ConsoleTable table = new ConsoleTable(data);
System.out.print(table);        // Note: table.toString() is called implicitly
```

## Quick Start

 * To get a quick demonstration, run ConsoleTable.java, the main() method prints out some samples.
 * To find out what Styles are available, run Styles.java, the main() method prints a sample of each predefined Style.
 * To display monospaced box-drawing characters in the Eclipse console, in Run | Run Configurations... , in the Common tab, set Encoding to Other UTF-8 or set the Project | Properties | Text File Encoding to UTF-8, so that it is inherited automatically by the Run Configurations.



