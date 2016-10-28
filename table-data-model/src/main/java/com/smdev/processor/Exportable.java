package com.smdev.processor;

import java.io.File;

import com.smdev.exc.WriteFileExaception;
import com.smdev.model.Table;
import com.smdev.model.TableProps;

public interface Exportable<P extends TableProps> {

	File write(P tableProps, Table data) throws WriteFileExaception;
}
