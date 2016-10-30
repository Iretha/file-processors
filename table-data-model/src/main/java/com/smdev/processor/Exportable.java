package com.smdev.processor;

import java.io.File;

import com.smdev.exc.ApplicationException;
import com.smdev.exc.WriteFileExaception;
import com.smdev.model.Table;
import com.smdev.model.TableProps;

/**
 * Interface used for exporting different file types.
 * 
 * @author Ireth
 * @param <P>
 *            - the type of the concrete table properties
 */
public interface Exportable<P extends TableProps> {
	/**
	 * Writes the given {@link Table} into file.
	 * 
	 * @param props
	 *            - properties needed for writing the file
	 * @param data
	 *            - data to write
	 * @return file - target file
	 * @throws WriteFileExaception
	 */
	File write(P props, Table data) throws ApplicationException;
}
