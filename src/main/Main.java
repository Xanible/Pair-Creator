package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* The purpose of this program is to read through a given collection of text files and print out a list of the unique
 * pairs that exist at a given step size. CHANGE THE VARIABLE STEP SIZE.
 */

public class Main {

	public static void main(String[] args) throws IOException {
		int maxStepSize = 20;

		for(int stepSize = 1; stepSize <= maxStepSize; stepSize++) {
			// Access the directory containing the directories that contain the files
			File sourceDir = new File("C:\\Users\\colby\\Desktop\\SCHOOL\\AndroidCT\\Cleaned Disassembly");

			// Create an array of the directories
			File[] dirs = sourceDir.listFiles();

			// Create hashmap and iterator
			HashMap<String, Integer> listOfPairs = new HashMap<String, Integer>();
			int count = 0;

			// Loop through directories
			for(File dir: dirs) {
				// Create list of files in current directory
				File[] dirFiles = dir.listFiles();

				// Loop through files
				for(File f: dirFiles) {
					count++;
					try {
						// Convert file to array list
						List<String> theFile = Files.readAllLines(f.toPath(), Charset.defaultCharset() );
						String[] words = theFile.get(0).split(" ");
						List<String> wordsList = new ArrayList<String>(Arrays.asList(words));

						// Go through the file
						for(int i = 0;i < wordsList.size() - stepSize;i++) {
							String s = wordsList.get(i) + " " + wordsList.get(i + stepSize);
							// Add pairing to hashmap
							listOfPairs.put(s, count);
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			// Create sets from hashmaps
			Set<String> uniqueValues = new HashSet<String>(listOfPairs.keySet()); 
			// Output number of unique values in the set
			System.out.println("The number of unique words for step size " + stepSize + ": "+uniqueValues.size());

			// Output pairs to file
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\colby\\Desktop\\SCHOOL\\AndroidCT\\"
					+ "Pairing Lists\\Pair" + stepSize + ".txt"));
			for(String s: uniqueValues) {
				bw.write(s);
				bw.newLine();
			}
			bw.close();
		}
	}
}
