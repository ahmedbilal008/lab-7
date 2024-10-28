package test;

import org.junit.jupiter.api.Test;
import sourcecode.FileSearcher;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileSearcherTest {

    @Test
    void testFileFoundCaseSensitive() throws Exception {
        // Setup: Create a temporary directory and file for testing
        String dirPath = Files.createTempDirectory("testDir").toString();
        String fileName = "TestFile.txt";
        Files.createFile(Paths.get(dirPath, fileName));

        // Test: Search for the file (case-sensitive)
        boolean found = FileSearcher.searchFile(new File(dirPath), fileName, false);
        assertTrue(found);

        // Cleanup
        Files.delete(Paths.get(dirPath, fileName));
        Files.delete(Paths.get(dirPath));
    }

    @Test
    void testFileNotFoundCaseSensitive() {
        // Test: Search for a non-existing file
        boolean found = FileSearcher.searchFile(new File("."), "nonExistingFile.txt", false);
        assertFalse(found);
    }

    @Test
    void testFileFoundCaseInsensitive() throws Exception {
        // Setup: Create a temporary directory and file for testing
        String dirPath = Files.createTempDirectory("testDir").toString();
        String fileName = "TestFile.txt";
        Files.createFile(Paths.get(dirPath, fileName));

        // Test: Search for the file (case-insensitive)
        boolean found = FileSearcher.searchFile(new File(dirPath), "testfile.txt", true);
        assertTrue(found);

        // Cleanup
        Files.delete(Paths.get(dirPath, fileName));
        Files.delete(Paths.get(dirPath));
    }

    @Test
    void testFileNotFoundCaseInsensitive() throws Exception {
        // Setup: Create a temporary directory and file for testing
        String dirPath = Files.createTempDirectory("testDir").toString();
        Files.createFile(Paths.get(dirPath, "TestFile.txt"));

        // Test: Search for a file that doesn't exist (case-insensitive)
        boolean found = FileSearcher.searchFile(new File(dirPath), "nonexistingfile.txt", true);
        assertFalse(found);

        // Cleanup
        Files.delete(Paths.get(dirPath, "TestFile.txt"));
        Files.delete(Paths.get(dirPath));
    }

    @Test
    void testFileFoundCaseSensitiveDifferentName() throws Exception {
        // Setup: Create a temporary directory and file for testing
        String dirPath = Files.createTempDirectory("testDir").toString();
        Files.createFile(Paths.get(dirPath, "TestFile.txt"));

        // Test: Search for the file with a different case (should not find)
        boolean found = FileSearcher.searchFile(new File(dirPath), "testfile.txt", false);
        assertFalse(found);

        // Cleanup
        Files.delete(Paths.get(dirPath, "TestFile.txt"));
        Files.delete(Paths.get(dirPath));
    }

    @Test
    void testEmptyDirectory() throws Exception {
        // Test: Search in an empty directory
        String emptyDirPath = "C:\\Users\\abilal.bese22seecs\\Desktop\\testdir\\test2";
        new File(emptyDirPath).mkdir(); // Create an empty directory
        boolean found = FileSearcher.searchFile(new File(emptyDirPath), "anyfile.txt", false);
        assertFalse(found, "File should not be found in an empty directory");

        // Cleanup
        new File(emptyDirPath).delete();
    }

    @Test
    void testNullFiles() {
        // Test: Search in a directory that does not exist
        File nonExistentDir = new File("C:\\Users\\abilal.bese22seecs\\Desktop\\nonexistentdir");
        boolean found = FileSearcher.searchFile(nonExistentDir, "anyfile.txt", false);
        assertFalse(found, "File should not be found in a non-existent directory");
    }
}
