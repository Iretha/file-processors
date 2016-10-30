Supported file types: xls, xlsx

==================================================
How to initialize table properties:

ExcelProps props = new ExcelProps();
props.setName("test"); // optional


==================================================
How to read an excel file:

ExcelFileReader reader = new ExcelFileReader();
File file = new File("example.xls");
Table table = reader.read(props, file);

==================================================
How to write an excel file from custom model or another imported file:

Table table = new Table(1); // read it from a file or create it programmaticaly
table.addRow("Col 1", "Col 2", "Col 3");
table.addRow(11, 22, 33);

ExcelFileWriter writer = new ExcelFileWriter();
File file = writer.write(ExcelProps props, Table table)

==================================================
How to write an excel file from java.sql.ResultSet:

ExcelFileWriter writer = new ExcelFileWriter();
File file = writer.write(ExcelProps props, ResultSet rs);

