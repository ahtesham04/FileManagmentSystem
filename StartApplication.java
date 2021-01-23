package com.filehandling.main;

import java.util.Scanner;

public class StartApplication {

	public static void main(String[] args) 
	{
		System.out.println("\nWelcome to LockedMe.com Application");
		System.out.println("Full Stack Developer - Ahtesham Ansari");
		System.out.println("This application is useful for file handling ");
		System.out.println("***********************************************************************************\n");
		Service service=new Service();
		Scanner scan = new Scanner(System.in);
		while(true) {

			service.menuList(scan);
		}
	}

}
