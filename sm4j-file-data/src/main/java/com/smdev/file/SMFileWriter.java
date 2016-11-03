package com.smdev.file;

import java.io.File;

import com.sm4j.exception.ApplicationException;
import com.smdev.exc.WriteFileExaception;
import com.smdev.model.Data;
import com.smdev.model.FileProps;

/**
 * Interface used for writing different file types.
 *
 * @author Ireth
 * @param <P>
 *            - the type of the concrete table properties
 */
public interface SMFileWriter<P extends FileProps> {
	/**
	 * Writes the given {@link Data} into file.
	 *
	 * @param props
	 *            - properties needed for writing the file
	 * @param data
	 *            - data to be written
	 * @return file - target file
	 * @throws WriteFileExaception
	 */
	File write(P props, Data data) throws ApplicationException;
}
