package sourcecode;

import java.io.File;
import java.util.Arrays;

public class FileSearcher {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java FileSearcher <directory> <file-name1> [<file-name2> ...] [--case-insensitive]");
            return;
        }
        
        String directoryPath = args[0];
        String[] fileNames = Arrays.copyOfRange(args, 1, args.length - (args[args.length - 1].equals("--case-insensitive") ? 1 : 0));
        boolean caseInsensitive = args[args.length - 1].equals("--case-insensitive");

        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified directory does not exist.");
            return;
        }

        for (String fileName : fileNames) {
            boolean found = searchFile(directory, fileName, caseInsensitive);
            if (!found) {
                System.out.println("File not found: " + fileName);
            }
        }
    }

    public static boolean searchFile(File directory, String fileName, boolean caseInsensitive) {
        File[] files = directory.listFiles();
        if (files == null) {
            return false;
        }

        for (File file : files) {
            boolean nameMatches = caseInsensitive 
                ? file.getName().equalsIgnoreCase(fileName) 
                : file.getName().equals(fileName);

            if (file.isDirectory()) {
                if (searchFile(file, fileName, caseInsensitive)) {
                    return true;
                }
            } else if (nameMatches) {
                System.out.println("File found: " + file.getAbsolutePath());
                return true;
            }
        }
        return false;
    }
}
