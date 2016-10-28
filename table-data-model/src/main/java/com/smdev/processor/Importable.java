package com.smdev.processor;

import java.io.File;

import com.smdev.exc.ModelException;
import com.smdev.exc.ReadFileException;
import com.smdev.model.Table;
import com.smdev.model.TableProps;

public interface Importable<P extends TableProps> {

	Table read(P tableProps, File file) throws ReadFileException, ModelException;

}
