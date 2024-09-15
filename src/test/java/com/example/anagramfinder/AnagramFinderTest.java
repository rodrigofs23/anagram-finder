package com.example.anagramfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;

class AnagramFinderTest {

  private final AnagramFinder anagramFinder = new AnagramFinder();
  private static final String TEST_INPUT =
      "akte teak kate aldri " + "arild aller ralle alt tal " + "andre rande denar ander";

  @Test
  void testSortString() {
    String sorted = anagramFinder.sortString("bca");
    assertEquals("abc", sorted, "Expected sorted string to be 'abc'");
  }

  @Test
  void testFindAnagrams_EmptyFile() throws Exception {
    InputStream emptyInputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));

    List<List<String>> anagrams = anagramFinder.findAnagrams(emptyInputStream);

    assertTrue(anagrams.isEmpty(), "Expected no anagrams for an empty file");
  }

  @Test
  void testAnagramSetsSize() throws Exception {
    InputStream inputStream = new ByteArrayInputStream(TEST_INPUT.getBytes(StandardCharsets.UTF_8));

    List<List<String>> anagrams = anagramFinder.findAnagrams(inputStream);

    assertEquals(5, anagrams.size(), "Expected 5 sets of anagrams");
  }

  @Test
  void testAnagramSetsContent() throws Exception {
    InputStream inputStream = new ByteArrayInputStream(TEST_INPUT.getBytes(StandardCharsets.UTF_8));

    List<List<String>> anagrams = anagramFinder.findAnagrams(inputStream);

    assertTrue(
        anagrams.stream().anyMatch(list -> list.containsAll(List.of("akte", "teak", "kate"))));
    assertTrue(anagrams.stream().anyMatch(list -> list.containsAll(List.of("aldri", "arild"))));
    assertTrue(anagrams.stream().anyMatch(list -> list.containsAll(List.of("aller", "ralle"))));
    assertTrue(anagrams.stream().anyMatch(list -> list.containsAll(List.of("alt", "tal"))));
    assertTrue(
        anagrams.stream()
            .anyMatch(list -> list.containsAll(List.of("andre", "rande", "denar", "ander"))));
  }
}
