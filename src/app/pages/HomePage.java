package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomePage implements Page {
    private ArrayList<Song> likedSongs;
    private ArrayList<Playlist> followedPlaylists;
    private static final int LIMIT = 4;

    public HomePage(final ArrayList<Song> likedSongs, final ArrayList<Playlist> followedPlaylists) {
        this.likedSongs = likedSongs;
        this.followedPlaylists = followedPlaylists;
    }

    /**
     * prints the home page
     *
     * @return the page
     */
    @Override
    public String printCurrentPage() {
        String message = "Liked songs:\n\t[";
        String aux = "";

        List<Song> sortedSongs = new ArrayList<>(likedSongs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());

        int size = sortedSongs.size();
        int count = 0;
        while (size != 0) {
            aux += sortedSongs.get(count).getName();
            if (size - 1 == 0) {
                break;
            }
            if (count < LIMIT) {
                aux += ", ";
            }
            if (count == LIMIT) {
                break;
            }

            count++;
            size--;
        }

        message += aux;
        message += "]\n\nFollowed playlists:\n\t[";

        aux = "";

        size = followedPlaylists.size();
        count = 0;
        while (size != 0) {
            aux += followedPlaylists.get(count).getName();
            if (size - 1 == 0) {
                break;
            }
            if (count < LIMIT) {
                aux += ", ";
            }
            if (count == LIMIT) {
                break;
            }

            count++;
            size--;
        }
        aux += "]";

        message += aux;
        return message;
    }
}
