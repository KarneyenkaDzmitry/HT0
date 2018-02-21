package Prj2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
/*The main class that has main method
* The class runs all parts of application. It creates HTML file with results of its work.*/
public class CatologizerMP3 {
    private static Path currentDir = Paths.get("").toAbsolutePath();//Current Directory
    private static Path htmlPath = Paths.get(currentDir + "\\Catologizer.html");//Path to HTML file that will keeps results
    private static Set<Path> paths = new TreeSet<>();//The Set keeps all paths of MP3 files
    private static Set<Mp3Metadata> mp3MetadataSet = new TreeSet<>();//It helps us to sort all objects Mp3Metadata @see Mp3Metadata.compareTo();
    private static Map<String, Set<Mp3Metadata>> mapOfMd5 = new HashMap<String, Set<Mp3Metadata>>();//Keeps Files that have equals MD5 key;
    private static Map<String, Set<Mp3Metadata>> mapOfName = new HashMap<String, Set<Mp3Metadata>>();//Keeps files that have equals artist + album + article;
    private static String help = "Recall the program and put paths of directories via space as arguments.";
/*The method checks input arguments for the rules program. Return true if the arguments are OK and false if they are wrong */
    private static boolean checkerArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            Path path = Paths.get(args[i]);
            /*Check path. Is exist and Is directory.*/
            if (!Files.exists(path)) {
                System.out.println("The wrong parameter: \"" + path + "\" It isn't exist!");
                return false;
            }
            if ((!Files.isDirectory(path))) {
                System.out.println("The wrong parameter: \"" + path + "\" It isn't directory!");
                return false;
            }
        }
        return true;
    }
/*Main method. */
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {// Check amount of arguments.
            if (checkerArgs(args)) {//checks the arguments for correctness
                for (String str : args) {//Finds and makes a set of all MP3 files in directories that input as arguments
                    Path path = Paths.get(str);
                    FileManager fileManager = new FileManager(path, ".mp3");
                    paths.addAll(fileManager.getFileList());
                }
                /*Write results on HTML file*/
                BufferedWriter writer = new BufferedWriter(new FileWriter(htmlPath.toFile()));
                writer.write(htmlString());
                writer.close();
                checkReplicate();//calls method that find duplicates of MP3 files
                System.out.println("The program has just finished successfully.");//The end.
            }
        } else {//If no arguments input to the program
            System.out.println("There aren't arguments.");
            System.out.println(help);
        }
    }
/*Create two maps with duplicates of files*/
    private static void addToMaps(Mp3Metadata mp3Metadata) {
        String md5 = mp3Metadata.getMd5();
        if (mapOfMd5.containsKey(md5)) {
            mapOfMd5.get(md5).add(mp3Metadata);
        } else {
            Set<Mp3Metadata> mp3MetadataSet = new TreeSet<>();
            mp3MetadataSet.add(mp3Metadata);
            mapOfMd5.put(md5, mp3MetadataSet);
        }
        String tags = mp3Metadata.getArtist() + mp3Metadata.getAlbum() + mp3Metadata.getTitle();
        if (mapOfName.containsKey(tags)) {
            mapOfName.get(tags).add(mp3Metadata);
        } else {
            Set<Mp3Metadata> mp3MetadataSet = new TreeSet<>();
            mp3MetadataSet.add(mp3Metadata);
            mapOfName.put(tags, mp3MetadataSet);
        }
    }
/*Write Data to the HTML file*/
    private static String htmlString() throws IOException {
        StringBuilder builder = new StringBuilder("<!DOCTYPE HTML>\n<HTML>\n<HEAD>\n<meta charset = \"utf-8\">\n" +
                "<title>Кaтологизатор MP3 файлов</title>\n</HEAD>\n<body>\n" +
                "<h1>Полный список MP3 файлов, в поступивших из командной строки, каталогах:</h1>\n");
        String artist = "";
        String album = "";
        for (Path path : paths) {//Adds Mp3Metadata(path) objects to TreeSet for sorting
            Mp3Metadata mp3Metadata = new Mp3Metadata(path);
            mp3MetadataSet.add(mp3Metadata);
            addToMaps(mp3Metadata);
        }
        for (Mp3Metadata mp3Metadata : mp3MetadataSet) {
            String currentArtist = mp3Metadata.getArtist();
            String currentAlbum = mp3Metadata.getAlbum();
            if (artist.equals(currentArtist)) {
                if (album.equals(currentAlbum)) {
                    builder.append("<p>&nbsp;&nbsp;&nbsp;Title: " + mp3Metadata.getTitle());
                    builder.append(String.format(" Duration: %.2f", mp3Metadata.getDuration()) + " (" + mp3Metadata.getPath() + ")</p>\n");
                } else {
                    album = currentAlbum;
                    builder.append("<p>&nbsp;&nbsp;Album: " + mp3Metadata.getAlbum() + "</p>\n");
                    builder.append("<p>&nbsp;&nbsp;&nbsp;Title: " + mp3Metadata.getTitle());
                    builder.append(String.format(" Duration: %.2f", mp3Metadata.getDuration()) + " (" + mp3Metadata.getPath() + ")</p>\n");
                }
            } else {
                artist = currentArtist;
                album = currentAlbum;
                builder.append("<p>&nbsp;Artist: " + mp3Metadata.getArtist() + "</p>\n");
                builder.append("<p>&nbsp;&nbsp;Album: " + mp3Metadata.getAlbum() + "</p>\n");
                builder.append("<p>&nbsp;&nbsp;&nbsp;Title: " + mp3Metadata.getTitle());
                builder.append(String.format(" Duration: %.2f", mp3Metadata.getDuration()) + " (" + mp3Metadata.getPath() + ")</p>\n");
            }
        }
        builder.append("</body>\n</HTML>");
        return builder.toString();
    }
/*Check maps of duplicates and add note to log file*/
    private static void checkReplicate() {
        System.setProperty("log4j.configurationFile", Paths.get("", "log4j2.xml").toString());
        Logger logger = LogManager.getRootLogger();
        for (Map.Entry<String, Set<Mp3Metadata>> pair : mapOfMd5.entrySet()) {
            Set<Mp3Metadata> mp3MetadataList = pair.getValue();
            if (mp3MetadataList.size() > 1) {
                logger.info("There are some equals files (duplicate A):");
                for (Mp3Metadata metadata : mp3MetadataList) {
                    logger.info(metadata.getPath());
                }
            }
        }
        for (Map.Entry<String, Set<Mp3Metadata>> pair : mapOfName.entrySet()) {
            Set<Mp3Metadata> mp3MetadataList = pair.getValue();
            if (mp3MetadataList.size() > 1) {
                logger.info("There can be some equals files (duplicate B):" + pair.getKey());
                for (Mp3Metadata metadata : mp3MetadataList) {
                    logger.info(metadata.getPath());
                }
            }
        }
    }
}


