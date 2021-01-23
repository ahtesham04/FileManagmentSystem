package com.filehandling.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class FileOperation {
	public boolean addFile(String fileName,String source,String dest) throws IOException {
		String src=source+"/"+fileName;
		String des=dest+"/"+fileName;
		File newFile=new File(des);
		if(newFile.exists()) {
			System.out.println("Directory Not Found, Please check it..");
			return false;
		}
		else if(!new File(src).exists()) {
			return newFile.createNewFile();
		}else {
			Path temp = Files.copy 
					(Paths.get(src),  
							Paths.get(des),StandardCopyOption.REPLACE_EXISTING); 
			return temp==null?false:true;  
		}
	}


	public boolean deleteFile(File file) {
		return file.delete(); 
	}

	public void searchFile(String fileName,File file,ArrayList<String>files) {
		File[] list = file.listFiles();
		if(list!=null) {
			for (File fil : list)
			{
				if (fil.isDirectory())
				{
					searchFile(fileName,fil,files);
				}else if (fileName.equals(fil.getName()))
				{
					files.add(fil.getAbsolutePath());
				}
			}
		}
	}
	public void getFileNamesInAscOrder(String path) {
		File directory = new File(path);
		if(directory.exists()) {
			ArrayList<String>listFiles=new ArrayList<>();
			listf(directory,listFiles);
			if(!listFiles.isEmpty()) {
				listFiles.forEach(file->{
					System.out.println(file);
				});
			}else {
				System.out.println(" File not Found or directory is empty..");
			}
		}else{
			System.out.println("Directory not found, please enter existing root directory");
		}
	}


	public ArrayList<String> listf(File directory, ArrayList<String>listFiles) {

		File[] fList = directory.listFiles();
		if(fList != null) {
			for (File file : fList) {      
				if (file.isFile()) {
					listFiles.add(file.getAbsolutePath());
				} else if (file.isDirectory()) {
					listf(file,listFiles);
				}
			}
			Collections.sort(listFiles);
		}

		return listFiles;
	}


}
