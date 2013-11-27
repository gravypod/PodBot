package com.gravypod.PodBot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 
 * File reading utilitys
 * 
 * @author ElgarL, Maker of GM and Towny
 * 
 */
public class FileReader {

	/**
	 * 
	 * Pass a file and it will return it's contents as a string.
	 * 
	 * @param file
	 *            File to read.
	 * @return Contents of file. String will be empty in case of any errors.
	 * 
	 */
	public static String convertFileToString(File file) {

		if (file != null && file.exists() && file.canRead() && !file.isDirectory()) {
			Writer writer = new StringWriter();
			InputStream is = null;

			char[] buffer = new char[1024];
			try {
				is = new FileInputStream(file);
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} catch (IOException e) {
				System.out.println("Exception ");
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException ignore) {
					}
				}
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	/**
	 * 
	 * Converts a file to a string array.
	 * 
	 * @param file
	 * @return
	 * 
	 */
	public static String[] fileToStringArray(File file) {

		return convertFileToString(file).split(System.getProperty("line.separator"));

	}

}
