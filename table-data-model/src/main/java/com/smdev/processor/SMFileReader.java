package com.smdev.processor;

import java.io.File;

import com.smdev.exc.ApplicationException;
import com.smdev.exc.ModelException;
import com.smdev.exc.ReadFileException;
import com.smdev.model.Data;
import com.smdev.model.FileProps;

/**
 * Interface used for reading different file types.
 * 
 * @author Ireth
 * @param <P>
 *            - the type of the concrete table properties
 */
public interface SMFileReader<P extends FileProps> {

	/**
	 * Reads the given source file and creates {@link Data}.
	 * 
	 * @param props
	 *            - properties needed for parsing the content
	 * @param file
	 *            - file to read
	 * @return table - parsed file data
	 * @throws ReadFileException
	 * @throws ModelException
	 */
	Data read(P props, File file) throws ApplicationException;

}
