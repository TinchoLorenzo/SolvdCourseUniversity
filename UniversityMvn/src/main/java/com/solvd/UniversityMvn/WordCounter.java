package com.solvd.UniversityMvn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

public class WordCounter {

	public static void main(String[] args)  {
		try {
			version1();
			version2();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public static void version1()throws IOException {
		//String text = FileUtils.readFileToString(new File("/src/main/resources/1000wordstext.txt"), "utf-8").toLowerCase();
		String text = FileUtils.readFileToString(new File(WordCounter.class.getResource("/1000wordstext.txt").getFile()), "utf-8").toLowerCase();
		Map<String, Integer> map = new HashMap<String, Integer>();
	    //List<String> cleanWordsArray = Arrays.asList(StringUtils.split(StringUtils.replace(StringUtils.replace(StringUtils.replace(text, ".", " "), ",", " "), "-", " ")));

		List<String> cleanWordsArray = Arrays.asList(StringUtils.split(RegExUtils.removeAll(text, Pattern.compile("[^a-zA-Z'\s\n]"))));
		for(String word: new HashSet<String>(cleanWordsArray)) 
			map.put(word, Collections.frequency(cleanWordsArray, word));
		FileUtils.writeLines(new File("./target/wordCounterResultVersion1.txt"), map.entrySet());
	}
	
	public static void version2() throws IOException{
		String text = FileUtils.readFileToString(new File(WordCounter.class.getResource("/1000wordstext.txt").getFile()), "utf-8").toLowerCase();
		List<String> cleanWordsArray = 	Stream.of(StringUtils.split(RegExUtils.removeAll(text, Pattern.compile("[^a-zA-Z'\s\n]")))).collect(Collectors.toList());
		FileUtils.writeLines(new File("./target/wordCounterResultVersion2.txt"),cleanWordsArray.stream().distinct().collect(Collectors.toMap(w -> w, w ->Collections.frequency(cleanWordsArray, w))).entrySet());
	}
	
}
