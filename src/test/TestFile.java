package test;

import org.junit.Test;

import main.FileProcessor;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TestFile {
	
	@Test
	public void TestCreateFile() throws IOException {
		String outputFile = "Testing_File.txt";
		FileProcessor.makeNotes(outputFile, "10 April 2010");
		List<String> result = Files.lines(Paths.get(outputFile)).collect(Collectors.toList());
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void TestDeleteFile() throws IOException {
		String outputFile = "Testing_File2.txt";
		FileProcessor.makeNotes(outputFile, "10 April 2010");
		FileProcessor.deleteNotes(outputFile);
		assertTrue(!Files.exists(Paths.get(outputFile)));
	}
}
