package com.smdev.exc;

import com.sm4j.exception.IMessageKey;

public enum DataModelMessageKey implements IMessageKey {

	arg_null, inv_arg, inv_arr_length, inv_row_values,

	test;

	@Override
	public String getBundleName() {
		return "DataModelMessages";
	}

	@Override
	public String getMessageKey() {
		return name();
	}
}
