package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import fileio.input.SongInput;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Album extends AudioCollection {
    private Integer releaseYear;
    private String description;
    private final ArrayList<Song> songs = new ArrayList<>();

    /**
     * Instantiates a new Album.
     *
     * @param name      the name
     * @param owner     the owner
     */
    public Album(final String name, final String owner,
                 final int releaseYear, final String description,
                 final ArrayList<SongInput> songs) {
        super(name, owner);

        for (SongInput songInput : songs) {
            this.songs.add(new Song(songInput.getName(), songInput.getDuration(),
                    songInput.getAlbum(), songInput.getTags(), songInput.getLyrics(),
                    songInput.getGenre(), songInput.getReleaseYear(), songInput.getArtist()));
        }
        this.releaseYear = releaseYear;
        this.description = description;
    }

    /**
     * gets the album likes
     *
     * @return number of likes
     */
    public int getAlbumLikes() {
        int likes = 0;
        for (Song song : songs) {
            likes += song.getLikes();
        }
        return likes;
    }

    /**
     * gets number of songs of an album
     *
     * @return the number of songs of an album
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    /**
     *
     * @param index the index
     * @return a song by its index in songs
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }
}
