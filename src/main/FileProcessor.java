package main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

public class FileProcessor {
	private static Scanner input = new Scanner(System.in);
	
	private FileProcessor() {	
	}
	
	public static void makeNotes(String location, String date) {
		String textLine = "";
		
		if (Files.exists(Paths.get(location))) {
			System.out.print("Note already exists. Do you want to overwrite it? (yes / type anything for no) ");
			System.out.println();
			if (input.nextLine().trim().toLowerCase().equals("yes")) {
				System.out.println("Please type \"#exit#\" (no uppercase and quotes) to save the note");
				createNoteLog(location);
				try (BufferedWriter newNote = Files.newBufferedWriter(Paths.get(location), StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
					newNote.write("Date: "+date);
					newNote.newLine();
					newNote.newLine();
					textLine = input.nextLine().trim();
					while (!textLine.equals("#exit#")) {
						newNote.write(textLine);
						newNote.newLine();
						textLine = input.nextLine().trim();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} else {
				System.out.println("Cancelled.");
			}
		} else {
			System.out.println("Please type \"#exit#\" (no uppercase and quotes) to save the note");
			createNoteLog(location);
			try (BufferedWriter newNote = Files.newBufferedWriter(Paths.get(location), StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
				newNote.write("Date: "+date);
				newNote.newLine();
				newNote.newLine();
				textLine = input.nextLine().trim();
				while (!textLine.equals("#exit#")) {
					newNote.write(textLine);
					newNote.newLine();
					textLine = input.nextLine().trim();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void deleteNotes(String location) {
		if (Files.exists(Paths.get(location))) {
			try {
				System.out.print("Are you sure you want to delete your note? (yes / type anything for no) ");
				if (input.nextLine().trim().toLowerCase().equals("yes")) {
					Files.delete(Paths.get(location));
					System.out.println("Note deleted.");
				} else {
					System.out.println("Cancelled.");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("Note doesn't exist.");
		}
	}
	
	public static void viewNotes(String location) {
		if (Files.exists(Paths.get(location))) {
			try {
				System.out.println(String.format("\n%1$30s", " ").replace(' ', '='));
				Files.lines(Paths.get(location), StandardCharsets.UTF_8).forEach(s -> System.out.println(s));
				System.out.println(String.format("\n%1$30s", " ").replace(' ', '='));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("Note doesn't exist.");
		}
	}
	
	public static void viewListNote() {
		Path location = Paths.get("CLI_Notes_Log.dat").toAbsolutePath();
		
		if (Files.exists(location)) {
			try {
				System.out.println(String.format("\n%1$30s", " ").replace(' ', '='));
				System.out.println("List of notes created:");
				Files.lines(location, StandardCharsets.UTF_8).map(s -> s = s.split(" === ")[1]).forEach(System.out::println);;
				System.out.println(String.format("\n%1$30s", " ").replace(' ', '='));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private static void createNoteLog(String fileLocation) {
		Path location = Paths.get("CLI_Notes_Log.dat").toAbsolutePath();
		
		if (Files.exists(location)) {
			try (BufferedWriter addLog = Files.newBufferedWriter(location, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
				addLog.newLine();
				addLog.write(LocalDateTime.now(ZoneId.systemDefault()).toString());
				addLog.write(" === ");
				addLog.write(Paths.get(fileLocation).toAbsolutePath().toString());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			try (BufferedWriter addLog = Files.newBufferedWriter(location, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
				addLog.write(LocalDateTime.now(ZoneId.systemDefault()).toString());
				addLog.write(" === ");
				addLog.write(Paths.get(fileLocation).toAbsolutePath().toString());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
