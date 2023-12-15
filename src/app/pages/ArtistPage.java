package app.pages;

import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Files.Event;
import app.audio.Files.Merch;
import app.audio.Files.Song;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArtistPage implements Page {
    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merches;

    public ArtistPage(final ArrayList<Album> albums, final ArrayList<Merch> merches, final ArrayList<Event> events) {
        this.albums = albums;
        this.merches = merches;
        this.events = events;
    }
    @Override
    public String printCurrentPage() {
        String message = "Albums:\n\t[";
        String aux = "";

        int size = albums.size();
        int count = 0;
        while (size != 0) {
            aux += albums.get(count).getName();
            if (size - 1 == 0) {
                break;
            }
            aux += ", ";

            count++;
            size--;
        }

        message += aux;
        message += "]\n\nMerch:\n\t[";

        aux = "";

        size = merches.size();
        count = 0;
        while (size != 0) {
            aux += merches.get(count).getName();
            aux += " - ";
            aux += merches.get(count).getPrice();
            aux += ":\n\t";
            aux += merches.get(count).getDescription();
            if (size - 1 == 0) {
                break;
            }
            aux += ", ";

            count++;
            size--;
        }

        message += aux;
        message += "]\n\nEvents:\n\t[";

        aux = "";

        size = events.size();
        count = 0;
        while (size != 0) {
            aux += events.get(count).getName();
            aux += " - ";
            aux += events.get(count).getDate();
            aux += ":\n\t";
            aux += events.get(count).getDescription();
            if (size - 1 == 0) {
                break;
            }
            aux += ", ";

            count++;
            size--;
        }

        message += aux;
        message += "]";

        return message;
    }
}
