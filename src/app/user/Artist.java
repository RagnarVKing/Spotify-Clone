package app.user;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.AlbumOutput;
import app.audio.Collections.Playlist;
import app.audio.Files.Event;
import app.audio.Files.Merch;
import app.audio.Files.Song;
import app.utils.Enums;
import fileio.input.SongInput;
import lombok.Getter;

import java.util.ArrayList;

public final class Artist extends User {
    @Getter
    private ArrayList<Album> albums;
    @Getter
    private ArrayList<Event> events;
    @Getter
    private ArrayList<Merch> merches;

    private static final int FIRST = 1;
    private static final int DAYLAST = 31;
    private static final int MONTHLAST = 12;
    private static final int YEARFIRST = 1900;
    private static final int YEARLAST = 2023;
    private static final int FEBRUARY = 2;
    private static final int FEBDAYS = 28;


    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int TEN = 10;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public Artist(final String username, final int age, final String city) {
        super(username, age, city);
        this.albums = new ArrayList<>();
        this.events = new ArrayList<>();
        this.merches = new ArrayList<>();
    }

    /**
     * adds an album
     *
     * @param username
     * @param name
     * @param songs
     * @param description
     * @param releaseYear
     * @return the string
     */
    public String addAlbum(final String username, final String name,
                           final ArrayList<SongInput> songs, final String description,
                           final int releaseYear) {

        for (Album album : albums) {
            if (album.getName().equals(name)) {
                return username + " has another album with the same name.";
            }
        }

        for (int i = 0; i < songs.size() - 1; i++) {
            for (int j = i + 1; j < songs.size(); j++) {
                if (songs.get(i).getName().equals(songs.get(j).getName())) {
                    return username + " has the same song at least twice in this album.";
                }
            }
        }
        Album album = new Album(name, username, releaseYear, description, songs);
        albums.add(album);
        Admin.getInstance().addAlbum(album);
        for (Song song : album.getSongs()) {
            Admin.getInstance().getAllSongs().add(song);
        }

        return username + " has added new album successfully.";
    }

    /**
     * removes an album
     *
     * @param username
     * @param name
     * @return the string
     */
    public String removeAlbum(final String username, final String name) {

        for (Album album : albums) {
            if (album.getName().equals(name)) {
                for (User user1 : Admin.getInstance().getUsers()) {
                    if (user1.getPlayer().getSource() != null) {
                        if (user1.getPlayer().getSource().getAudioFile().matchesAlbum(name)) {
                            return username + " can't delete this album.";
                        }
                    }
                }
                for (User user1 : Admin.getInstance().getUsers()) {
                    if (user1.getPlayer().getSource() != null) {
                        if (user1.getPlayer().getSource().getType()
                                == Enums.PlayerSourceType.PLAYLIST) {
                            for (Song song1 : ((Playlist) user1.getPlayer().getSource()
                                    .getAudioCollection()).getSongs()) {
                                if (song1.matchesAlbum(name)) {
                                    return username + " can't delete this album.";
                                }
                            }
                        }
                    }
                }

                for (Song song : album.getSongs()) {
                    Admin.getInstance().getAllSongs()
                            .remove(Admin.getInstance().getSong(song.getName()));
                    for (User user1 : Admin.getInstance().getUsers()) {
                        user1.getLikedSongs().remove(user1.getLikedSong(song.getName()));
                    }
                }
                albums.remove(album);

                return username + " deleted the album successfully.";
            }
        }

        return username + " doesn't have an album with the given name.";
    }

    /**
     * shows all the albums
     *
     * @return an array of album names
     */
    public ArrayList<AlbumOutput> showAlbums() {
        ArrayList<AlbumOutput> albumOutputs = new ArrayList<>();
        for (Album album : albums) {
            albumOutputs.add(new AlbumOutput(album));
        }

        return albumOutputs;
    }

    /**
     * verify if the date of an event is valid
     *
     * @param username
     * @param date
     * @return the string
     */
    public String validateDate(final String username, final String date) {
        int day = Integer.parseInt(date.substring(ZERO, TWO));
        int month = Integer.parseInt(date.substring(THREE, FIVE));
        int year = Integer.parseInt(date.substring(SIX, TEN));

        if (day < FIRST || day > DAYLAST) {
            return "Event for " + username + " does not have a valid date.";
        }
        if (month < FIRST || month > MONTHLAST) {
            return "Event for " + username + " does not have a valid date.";
        }
        if (month == FEBRUARY) {
            if (day > FEBDAYS) {
                return "Event for " + username + " does not have a valid date.";
            }
        }
        if (year < YEARFIRST || year > YEARLAST) {
            return "Event for " + username + " does not have a valid date.";
        }

        return null;
    }

    /**
     * adds an event
     *
     * @param username
     * @param name
     * @param description
     * @param date
     * @return the string
     */
    public String addEvent(final String username, final String name,
                           final String description, final String date) {
        for (Event event : events) {
            if (event.getName().equals(name)) {
                return username + " has another event with the same name.";
            }
        }

        String message = validateDate(username, date);
        if (message != null) {
            return message;
        }
        events.add(new Event(name, description, date));
        return username + " has added new event successfully.";
    }

    /**
     * adds a merch
     *
     * @param username
     * @param name
     * @param description
     * @param price
     * @return the string
     */
    public String addMerch(final String username, final String name,
                           final String description, final int price) {
        for (Merch merch : merches) {
            if (merch.getName().equals(name)) {
                return username + " has merchandise with the same name.";
            }
        }
        if (price < 0) {
            return "Price for merchandise can not be negative.";
        }
        merches.add(new Merch(name, description, price));
        return username + " has added new merchandise successfully.";
    }

    /**
     * removes an event
     *
     * @param username
     * @param name
     * @return the string
     */
    public String removeEvent(final String username, final String name) {
        for (Event event : events) {
            if (event.getName().equals(name)) {
                events.remove(event);
                return username + " deleted the event successfully.";
            }
        }

         return username + " doesn't have an event with the given name.";
    }

    /**
     * gets artist likes
     *
     * @return the artist likes
     */
    public int getArtistLikes() {
        int likes = 0;
        for (Album album : albums) {
            for (Song song : album.getSongs()) {
                likes += song.getLikes();
            }
        }
        return likes;
    }
}
