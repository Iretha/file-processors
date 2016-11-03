package com.smdev.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Useful tools for manipulating resources
 *
 * @author Ireth
 */
public class ResourceUtils {

	/**
	 * Closes a resource, implementing {@link Closeable} without throwing an
	 * exception. The exception will be only logged.
	 *
	 * @param closeable
	 */
	public static void silentClose(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				// TODO log me
				e.printStackTrace();
			}
		}
	}

}
