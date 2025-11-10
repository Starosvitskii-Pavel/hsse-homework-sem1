package com.mipt.pavelstarosvitskiy.io;

import java.io.*;

public class TextFileAnalyzer {
  public static class AnalysisResult {
    private final long lineCount;
    private final long wordCount;
    private final long charCount;

    public AnalysisResult(long lineCount, long wordCount, long charCount) {
      this.lineCount = lineCount;
      this.wordCount = wordCount;
      this.charCount = charCount;
    }

    public long getLineCount() {
      return lineCount;
    }

    public long getWordCount() {
      return wordCount;
    }

    public long getCharCount() {
      return charCount;
    }

    @Override
    public String toString() {
      return "AnalysisResult{" +
          "lineCount=" + lineCount +
          ", wordCount=" + wordCount +
          ", charCount=" + charCount +
          '}';
    }
  }

  public AnalysisResult analyzeFile(String filePath) throws IOException {
    long lineCount = 0;
    long wordCount = 0;
    long charCount = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line = reader.readLine();
        while (line != null) {
          lineCount++;

          String[] words = line.trim().split("\\s+");
          wordCount += words.length;

          charCount += line.length();

          line = reader.readLine();
        }
    } catch (IOException e) {
      System.err.println("Could not read file: " + e.getMessage());
    }
    return new AnalysisResult(lineCount, wordCount, charCount);
  }

  public void saveAnalysisResult(AnalysisResult result, String outputPath) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
      writer.write(result.toString());
    }
  }
}
