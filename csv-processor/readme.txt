TODO

Example code:

How to initialize table properties:

CsvTableProps props = new CsvTableProps();
props.setSeparator(','); // required

props.setEscape(Character.MIN_VALUE); // optional
props.setExportHeaders(true); // optional
props.setName("test"); // optional
props.setQuote(Character.MIN_VALUE); // optional
props.setSkipFirstRows(0); // optional

==================================================
	
How to read a csv file:

CsvFileReader reader = new CsvFileReader();
File file = new File("example.csv");
Table table = reader.read(props, file);

==================================================
	
How to write a csv file:

Table table = new Table(1); // read it from a file or create it programmaticaly
table.addRow("Col 1", "Col 2", "Col 3");
table.addRow(11, 22, 33);

CsvFileWriter writer = new CsvFileWriter();
writer.write(props, table)