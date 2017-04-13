package main;

import java.util.Scanner;

public class CLINotes {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int choice = -1;
		
		while (choice != 0) {
			printMenu();
			choice = input.nextInt();
			if (choice == 0) {
				input.nextLine();
				System.out.println("CLI Notes closing.");
				input.close();
			} else if (choice == 1) {
				input.nextLine();
				System.out.print("\nPlease insert the name of the note. It can also include the file path.\n Insert here: ");
				String location = input.nextLine().trim();
				System.out.print("Please insert current date (e.g. 12 April 2017): ");
				String date = input.nextLine().trim();
				FileProcessor.makeNotes(location, date);
			} else if (choice == 2) {
				input.nextLine();
				System.out.print("\nPlease insert the name of the note. It can also include the file path.\n Insert here: ");
				String location = input.nextLine().trim();
				FileProcessor.deleteNotes(location);
			} else if (choice == 3) {
				input.nextLine();
				System.out.print("\nPlease insert the name of the note. It can also include the file path.\n Insert here: ");
				String location = input.nextLine().trim();
				FileProcessor.viewNotes(location);
			} else if (choice == 4) {
				input.nextLine();
				FileProcessor.viewListNote();
			} else {
				input.nextLine();
				System.out.println("Please insert 1 or 2. Insert 0 to exit the program.");
			}
		}
	}
	
	private static void printMenu() {
		System.out.println("\n\nWelcome to CLI Notes");
		System.out.println(String.format("%1$20s", " ").replace(' ', '#'));
		System.out.println(String.format("%1$s\n%2$s\n%3$s\n%4$s", "1. Make a note", "2. Delete a note", "3. View a note", "4. View list of notes"));
		System.out.print("Your choice: ");
	}
}