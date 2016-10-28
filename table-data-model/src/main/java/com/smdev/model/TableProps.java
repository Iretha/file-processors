package com.smdev.model;

import java.util.Properties;

import com.smdev.util.FileTypes;

public abstract class TableProps {

	private enum GeneralPropsKeys implements TablePropKey {
		/** */
		NAME, 
		/** */
		EXTENSION;
	}

	private final Properties props = new Properties();

	protected TableProps(FileTypes fileType) {
		super();
		put(GeneralPropsKeys.EXTENSION, fileType.getExtension());
	}

	public void addName(String value) {
		put(GeneralPropsKeys.NAME, value);
	}
	
	public String getName(){
		return get(GeneralPropsKeys.NAME, "exampleFile");
	}
	
	public String getNameWithExtension(){
		return getName() + "." + get(GeneralPropsKeys.EXTENSION, "txt");
	}

	private Properties getProps() {
		return props;
	}

	protected String get(TablePropKey key, String defaultValue) {
		return getProps().getProperty(key.name(), defaultValue);
	}
	
	protected boolean getBoolean(TablePropKey key, String defaultValue) {
		return Boolean.parseBoolean(get(key, defaultValue));
	}
	protected void put(TablePropKey key, String value) {
		getProps().setProperty(key.name(), value);
	}
	
	protected void put(TablePropKey key, int value) {
		put(key, String.valueOf(value));
	}
	
	protected void put(TablePropKey key, boolean value) {
		put(key, String.valueOf(value));
	}

}
