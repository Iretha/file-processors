package com.smdev.processor;

import java.io.File;

import com.smdev.exc.ModelException;
import com.smdev.exc.ReadFileException;
import com.smdev.model.Table;
import com.smdev.model.TableProps;

/**
 * Interface used for importing different file types.
 * 
 * @author Ireth
 * @param <P>
 *            - the type of the concrete table properties
 */
public interface Importable<P extends TableProps> {

	/**
	 * Reads the given source file and creates {@link Table}.
	 * 
	 * @param props
	 *            - properties needed for parsing the content
	 * @param file
	 *            - file to read
	 * @return table - parsed file data
	 * @throws ReadFileException
	 * @throws ModelException
	 */
	Table read(P props, File file) throws ReadFileException, ModelException;

}
