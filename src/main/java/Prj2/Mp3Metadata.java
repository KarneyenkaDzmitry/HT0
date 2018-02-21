package Prj2;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
/*Class describes states and behaviour. It keeps Metadata of file that is  located at path*/
public class Mp3Metadata implements Comparable<Mp3Metadata> {

    private String artist = "No name artist ";//Default state
    private String album = "No name album ";//Default state
    private String title = "No title";//Default state
    private double duration = 0.0;//Default state
    private String md5;//Hash of file
    private Path path;//Files path that has the metadata

/*Constructor receives one argument - path of file*/
    public Mp3Metadata(Path path) throws IOException {
        if (Files.exists(path)) {
            if (Files.isRegularFile(path)) {
                this.path = path;
                findTagsOfMP3(path);
            } else {
                System.out.println("It isn't regular file: " + path);
            }
        } else {
            throw new FileNotFoundException("File doesn't exist: " + path);
        }
    }

    private void findTagsOfMP3(Path path) throws IOException {
        InputStream input = new FileInputStream(path.toFile());
        ContentHandler handler = new DefaultHandler();
        Metadata metadata = new Metadata();
        Parser parser = new Mp3Parser();
        ParseContext parseCtx = new ParseContext();
        try {
            parser.parse(input, handler, metadata, parseCtx);
            String currentLine = metadata.get("xmpDM:albumArtist");
            if ((currentLine != null) && (!currentLine.isEmpty())) {
                artist = currentLine;
            }
            currentLine = metadata.get("xmpDM:album");
            if ((currentLine != null) && (!currentLine.isEmpty())) {
                album = currentLine;
            }
            currentLine = metadata.get("title");
            if ((currentLine != null) && (!currentLine.isEmpty())) {
                title = currentLine;
            }
            long lg;
            if (metadata.get("xmpDM:duration") != null) {
                double dur = Double.parseDouble(metadata.get("xmpDM:duration"));
                lg = (long) (((dur) / 60000) * 100);
                duration = (lg / 100.0);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
        input.close();
        InputStream inputMd = new FileInputStream(path.toFile());
        md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(inputMd);
        inputMd.close();
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public String getMd5() {
        return md5;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public int compareTo(@NotNull Mp3Metadata o) {
        int result;
        result = this.artist.compareTo(o.artist);
        if (result != 0) return result;
        result = this.album.compareTo(o.album);
        if (result != 0) return result;
        result = this.title.compareTo(o.title);
        if (result != 0) return result;
        result = this.md5.compareTo(o.md5);
        if (result != 0) return result;
        result = this.path.compareTo(o.path);
        if (result != 0) return result;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mp3Metadata)) return false;
        Mp3Metadata that = (Mp3Metadata) o;
        return Double.compare(that.getDuration(), getDuration()) == 0 &&
                Objects.equals(getArtist(), that.getArtist()) &&
                Objects.equals(getAlbum(), that.getAlbum()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getMd5(), that.getMd5()) &&
                Objects.equals(getPath(), that.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArtist(), getAlbum(), getTitle(), getDuration(), getMd5(), getPath());
    }
}

