package com.mipt.pavelstarosvitskiy.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

  /**
   * Разбивает файл на части указанного размера
   * @param sourcePath путь к исходному файлу
   * @param outputDir директория для сохранения частей
   * @param partSize размер каждой части в байтах
   * @return список путей к созданным частям
   */
  public List<Path> splitFile(String sourcePath, String outputDir, int partSize) throws IOException {
    Path srcPath = Path.of(sourcePath);
    List<Path> partPathList = new ArrayList<>();

    try (FileChannel channel = FileChannel.open(srcPath, StandardOpenOption.READ)) {
      ByteBuffer buffer = ByteBuffer.allocate(partSize);

      int partNumber = 1;
      while (channel.read(buffer) > 0) {
        Path partPath = Paths.get(outputDir, srcPath.getFileName() + ".part" + partNumber);
        partPathList.add(partPath);
        buffer.flip();

        try (FileChannel writeChannel = FileChannel.open(partPath,
            StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
          writeChannel.write(buffer);
        }

        buffer.clear();
        partNumber++;
      }
    }
    return partPathList;
  }

  /**
   * Объединяет части файла обратно в один файл
   * @param partPaths список путей к частям файла (в правильном порядке)
   * @param outputPath путь для результирующего файла
   */
  public void mergeFiles(List<Path> partPaths, String outputPath) throws IOException {
    try (FileChannel writeChannel =
             FileChannel.open(Path.of(outputPath), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
      for (Path partPath : partPaths) {
        if (!Files.exists(partPath)) throw new FileNotFoundException("Part does not exist: " + partPath);

        try (FileChannel readChannel = FileChannel.open(partPath, StandardOpenOption.READ)) {
          ByteBuffer buffer = ByteBuffer.allocate(4096);

          while (readChannel.read(buffer) > 0) {
            buffer.flip();
            writeChannel.write(buffer);
            buffer.clear();
          }
        }
      }
    }
  }
}