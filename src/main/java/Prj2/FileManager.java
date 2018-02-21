package Prj2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;
/*Finds all files with inputing suffix and adds them to Set*/
public class FileManager {
    private Path rootPath;//Root directory that inputs in constructor
    private Set<Path> fileList;//Set keeps all discovered files
    private String suffix;// Suffix that inputs via constructor
/*Constructor with two parameters: 1.Path of directory; 2.Suffix */
    public FileManager(Path rootPath, String suffix) throws IOException {
        this.rootPath = rootPath;
        fileList = new TreeSet<>();
        collectFileList(rootPath, suffix);
        this.suffix = suffix;
    }
/*Getter.Return Set of all discovered files*/
    public Set<Path> getFileList() {
        return fileList;
    }
/*the method find and add files to the set*/
    private void collectFileList(Path path, String suffix) throws IOException {
        if (Files.isRegularFile(path)) {
            if (path.toString().endsWith(suffix)) {
                fileList.add(path.toAbsolutePath());
            }
        }
        if (Files.isDirectory(path)) {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
            for (Path file : directoryStream) {
                collectFileList(file, suffix);
            }
        }
    }
}


