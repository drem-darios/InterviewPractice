import java.util.*;

/**
 * Checks if a playlist has a repeating son
 */
public class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {
        Set songList = new HashSet();
        songList.add(name);
        Song songRef = nextSong;
        while(songRef != null) {
            if (songList.contains(songRef.name)) {
                return true;
            }
            songList.add(songRef.name);
            songRef = songRef.nextSong;
        }

        return false;
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}