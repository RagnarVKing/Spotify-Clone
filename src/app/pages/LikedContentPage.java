package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.ArrayList;

public class LikedContentPage implements Page {
    private ArrayList<Song> likedSongs;
    private ArrayList<Playlist> followedPlaylists;

    public LikedContentPage(final ArrayList<Song> likedSongs,
                            final ArrayList<Playlist> followedPlaylists) {
        this.likedSongs = likedSongs;
        this.followedPlaylists = followedPlaylists;
    }

    /**
     * prints the liked content page
     *
     * @return the page
     */
    @Override
    public String printCurrentPage() {
        String message = "Liked songs:\n\t[";
        String aux = "";

        int size = likedSongs.size();
        int count = 0;
        while (size != 0) {
            aux += likedSongs.get(count).getName();
            aux += " - ";
            aux += likedSongs.get(count).getArtist();
            if (size - 1 == 0) {
                break;
            }
            aux += ", ";

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
            aux += " - ";
            aux += followedPlaylists.get(count).getOwner();
            if (size - 1 == 0) {
                break;
            }
            aux += ", ";

            count++;
            size--;
        }
        aux += "]";

        message += aux;
        return message;
    }
}
