package net.lars.game2.utils.fileUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 	
 * @Date 21. juni 2018
 * @Author LarsL123, Credit: ThinMatrix
 *
 * @Info:
 * 		Holds the information about a file.
 *
 * @TODO:
 * 		Understand the parts width the string array.
 * 
 * @Suggestions:
 *
 */
public class MyFile {
	private String path;
	private String name;
	
	
	public MyFile(String path) {  
		this.path =  FileUtils.FILE_SEPARATOR + path;
		String[] dirs = path.split(FileUtils.FILE_SEPARATOR);
		this.name = dirs[dirs.length - 1];
	}
	public MyFile(String... paths) {
		this.path = "";
		for (String part : paths) {
			this.path += (FileUtils.FILE_SEPARATOR + part);
		}
		String[] dirs = path.split(FileUtils.FILE_SEPARATOR);
		this.name = dirs[dirs.length - 1];
	}

	public MyFile(MyFile file, String subFile) {
		this.path = file.path + FileUtils.FILE_SEPARATOR + subFile;
		this.name = subFile;
	}
	
	public MyFile(MyFile file, String... subFiles) {
		this.path = file.path;
		for (String part : subFiles) {
			this.path += (FileUtils.FILE_SEPARATOR + part);
		}
		String[] dirs = path.split(FileUtils.FILE_SEPARATOR);
		this.name = dirs[dirs.length - 1];
	}

	public String getPath() {
		return path;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
		return getPath();
	}

	public InputStream getInputStream() {
		return this.getClass().getResourceAsStream(path);
	}
	
	public URL getUrl(){
		return this.getClass().getResource(path);
	}

//	public CSVReader openCsvReader() throws Exception {
//		return new CSVReader(this);
//	}

	public BufferedReader getReader() throws Exception {
		try {
			InputStreamReader isr = new InputStreamReader(getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			return reader;
		} catch (Exception e) {
			System.err.println("Couldn't get reader for " + path);
			throw e;
		}
	}
}
