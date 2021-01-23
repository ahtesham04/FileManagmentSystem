package com.filehandling.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Service {
	FileOperation fileOperation=null;
	Scanner scanner=null;

	public Service() {
		fileOperation=new FileOperation();
		scanner=new Scanner(System.in);
	}

	public void addFile() {
		System.out.println("IF you want to Create and Add new file,Please enter correct destination path");
		try {
			System.out.println("Please enter the file name");
			String fileName=scanner.next();
			System.out.println("Please enetr the source path");
			String source=scanner.next();
			System.out.println("Please enter destination path");
			String dest=scanner.next();
			boolean res=fileOperation.addFile(fileName, source, dest);
			if(res) {
				System.out.println("File Added Successfully..");
				this.takeInput();
			}
		} catch (IOException e) {
			if(e instanceof NoSuchFileException) {
				System.out.println("No Such File or Directory exists,going to main menu");
			}else {
				e.printStackTrace();
				System.out.println("Something went wrong,please check the above logs,going to main menu");
			}
			this.menuList(this.scanner);
		}

	}

	public void deleteFile() {
		System.out.println("Enter fileName..");
		String fileName=scanner.next();
		System.out.println("Enter File Path..");
		String path=scanner.next();
		if(!new File(path).exists()) {
			System.out.println("No Such Directory Found..");
			return;
		}
		File file=new File(path+"/"+fileName);
		if(file.exists()) {
			boolean res=fileOperation.deleteFile(file);
			if(res) {
				System.out.println("File deleted successfully..");
				this.menuList(this.scanner);
			}else {
				System.out.println("Unable to delete the file,check file path or filename..");
				this.menuList(this.scanner);
			}
		}else {
			System.out.println("File not Found..");
			this.menuList(this.scanner);
		}

	}

	public void searchFile() {
		System.out.println("Enter the fileName...");
		String fileName=scanner.next();
		System.out.println("Enter the directory..");
		String dir=scanner.next();
		File file=new File(dir);
		if(file.exists()) {
			ArrayList<String>files=new ArrayList<>();
			fileOperation.searchFile(fileName, file,files);
			if(files.isEmpty()) {
				System.out.println("File not Found..");
			}else {
				files.forEach(f->{
					System.out.println(f);
				});
			}
		}else {
			System.out.println("Directory not found..");
		}

		takeInput();
	}

	public void viewFiles() {
		System.out.println("Enter the existing root directory path..");
		String dir=scanner.next();
		fileOperation.getFileNamesInAscOrder(dir);
		takeInput();
	}

	public void options(int option) {
		switch (option) {
		case 0:
			System.out.println("Thanks for using LockedMe.com application,Bye Bye!");
			System.exit(1);
			break;
		case 1:
			this.viewFiles();

			break;
		case 2:
			this.otherOperations(this.scanner);
			break;
		case 3:
			this.addFile();
			break;
		case 4:
			this.deleteFile();
			break;
		case 5:
			this.searchFile();
			break;
		case 6:
			this.menuList(this.scanner);
			break;
		default:
			System.out.println("No Option matched,Enter the correct option");
			break;
		}
	}

	public void menuList(Scanner scan) {
		System.out.println("Welcom to Main menu");
		menuListHelper();
		int option = scan.nextInt();
		if(option==0 || option==1 || option==2) {
			options(option);
		}
		else {
			System.out.println("Invailid input, Please enter only given option");
			menuList(scan);
		}

	}
	public void menuListHelper() {

		System.out.println("Enter 1 to Retrieving the file names in an ascending order from the existing directory");
		System.out.println("Enter 2 to other operations");
		System.out.println("Enter 0 to close the applications");
	}

	public void otherOperations(Scanner scanner) {
		System.out.println("Welcome to other file operation");
		System.out.println("Enter 3 to Add a file to the existing directory list");
		System.out.println("Enter 4 to Delete a file from the existing directory list");
		System.out.println("Enter 5 to Search a file from the existing directory");
		System.out.println("Enter 6 for main menu");
		System.out.println("Enter 0 to exit");
		int option = scanner.nextInt();
		if(option==3 || option==4 || option==5) {
			options(option);
		}else if(option==0 || option==6 ) {
			options(option);
		}
		else {
			System.out.println("Invailid input, Please enter only given option");
			otherOperations(scanner);
		}
	}
	public void takeInput() {
		System.out.println("\nEnter 0 to exit or enter 6 to go to main menu");
		int opt=scanner.nextInt();
		if(opt==0) {
			options(0);
		}else if(opt==6) {
			this.menuList(this.scanner);
		}
		else {
			System.out.println("Invalid Option.Going to main menu");
			this.menuList(scanner);
		}
	}
}

