package com.example.anagramfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AnagramFinder {

  public static void main(String[] args) {
    try (InputStream inputStream =
        AnagramFinder.class.getClassLoader().getResourceAsStream("ordbok-utf8.txt")) {
      if (inputStream == null) {
        System.err.println("Resource file not found!");
        return;
      }
      var anagrams = new AnagramFinder().findAnagrams(inputStream);
      anagrams.forEach(list -> System.out.println(String.join(" ", list)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<List<String>> findAnagrams(InputStream inputStream) throws IOException {
    var anagramsMap = new HashMap<String, List<String>>();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      reader
          .lines()
          .flatMap(line -> Arrays.stream(line.trim().split("\\s+")))
          .filter(word -> !word.isEmpty())
          .map(String::toLowerCase)
          .forEach(
              word -> {
                var sortedWord = sortString(word);
                anagramsMap.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
              });
    }
    return anagramsMap.values().stream().filter(list -> list.size() > 1).toList();
  }

  public String sortString(String input) {
    char[] chars = input.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }
}
