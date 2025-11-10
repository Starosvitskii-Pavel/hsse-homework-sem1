package com.mipt.pavelstarosvitskiy.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TextFileAnalyzerTest {
  @Test
  void testAnalyzeFile() throws IOException {
    TextFileAnalyzer analyzer = new TextFileAnalyzer();

    Path testFile = Files.createTempFile("test", ".txt");
    Files.write(testFile, Arrays.asList("Hello world!", "This is test."));

    TextFileAnalyzer.AnalysisResult result = analyzer.analyzeFile(testFile.toString());

    assertEquals(2, result.getLineCount());
    assertEquals(5, result.getWordCount());
    assertEquals(25, result.getCharCount());
  }

  @Test
  void testSaveAnalysisResult() throws IOException {
    TextFileAnalyzer analyzer = new TextFileAnalyzer();

    TextFileAnalyzer.AnalysisResult result = new TextFileAnalyzer.AnalysisResult(2, 5, 20);

    Path outputFile = Files.createTempFile("analysis", ".txt");
    analyzer.saveAnalysisResult(result, outputFile.toString());

    assertTrue(Files.exists(outputFile));
    assertTrue(Files.size(outputFile)>0);

    try (BufferedReader reader = new BufferedReader(new FileReader(outputFile.toString()))) {
      String line = reader.readLine();
      assertEquals(result.toString(), line);

      assertNull(reader.readLine());
    }
  }
}