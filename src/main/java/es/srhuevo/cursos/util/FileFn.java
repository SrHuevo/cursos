package es.srhuevo.cursos.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileFn {
	public static void saveFile(String fileName, InputStream ins) throws IOException{
		byte[] buffer = new byte[1024];
		int leidos = 0;
		FileOutputStream fout = new FileOutputStream(fileName);
		while((leidos = ins.read(buffer)) != -1){
			fout.write(buffer, 0, leidos);
		}
		fout.close();
	}
}
