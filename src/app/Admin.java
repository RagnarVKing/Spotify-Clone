package app;

import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import app.utils.Enums;
import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The type Admin.
 */
public final class Admin {
    private static Admin instance;
    private static List<User> users = new ArrayList<>();
    private static List<Artist> artists = new ArrayList<>();
    private static List<Host> hosts = new ArrayList<>();
    private static List<Song> songs = new ArrayList<>();
    private static List<Podcast> podcasts = new ArrayList<>();
    private static List<Album> albums = new ArrayList<>();
    private static int timestamp = 0;
    private static final int LIMIT = 5;

    private Admin() {
    }

    /**
     * gets the instance of admin
     *
     * singleton
     *
     * @return the admin
     */
    public static Admin getInstance() {
        if (instance == null) {
            synchronized (Admin.class) {
                if (instance == null) {
                    instance = new Admin();
                }
            }
        }
        return instance;
    }

    /**
     * Sets users.
     *
     * @param userInputList the user input list
     */
    public static void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        }
    }

    /**
     * Sets songs.
     *
     * @param songInputList the song input list
     */
    public static void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }


    /**
     * Sets podcasts.
     *
     * @param podcastInputList the podcast input list
     */
    public static void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                                         episodeInput.getDuration(),
                                         episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * gets all songs
     *
     * @return the list of songs
     */
    public List<Song> getAllSongs() {
        return songs;
    }

    /**
     * Gets podcasts.
     *
     * @return the podcasts
     */
    public List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     * gets all artists
     *
     * @return the artists
     */
    public List<Artist> getArtists() {
        return new ArrayList<>(artists);
    }

    /**
     * gets all users
     *
     * @return the users
     */
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    /**
     * gets all hosts
     *
     * @return the hosts
     */
    public List<Host> getHosts() {
        return new ArrayList<>(hosts);
    }

    /**
     * gets all albums
     *
     * @return the albums
     */
    public List<Album> getAlbums() {
        return new ArrayList<>(albums);
    }

    /**
     * adds an album
     *
     * @param album
     */
    public void addAlbum(final Album album) {
        albums.add(album);
    }

    /**
     * adds a podcast
     *
     * @param podcast
     */
    public void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * adds a song
     *
     * @param songInput
     */
    public void addSong(final SongInput songInput) {
        songs.add(new Song(songInput.getName(), songInput.getDuration(),
                songInput.getAlbum(), songInput.getTags(), songInput.getLyrics(),
                songInput.getGenre(), songInput.getReleaseYear(), songInput.getArtist()));
    }

    /**
     * Gets playlists.
     *
     * @return the playlists
     */
    public List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * gets a song by name
     *
     * @param name
     * @return the song
     */
    public Song getSong(final String name) {
        for (Song song : songs) {
            if (song.getName().equals(name)) {
                return song;
            }
        }
        return null;
    }

    /**
     * gets an artist by username
     *
     * @param username
     * @return the artist
     */
    public Artist getArtist(final String username) {
        for (Artist artist : artists) {
            if (artist.getUsername().equals(username)) {
                return artist;
            }
        }
        return null;
    }

    /**
     * gets a host by username
     *
     * @param username
     * @return the host
     */
    public Host getHost(final String username) {
        for (Host host : hosts) {
            if (host.getUsername().equals(username)) {
                return host;
            }
        }
        return null;
    }

    /**
     * Update timestamp.
     *
     * @param newTimestamp the new timestamp
     */
    public void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (User user : users) {
            user.simulateTime(elapsed);
        }
    }

    /**
     * Gets top 5 songs.
     *
     * @return the top 5 songs
     */
    public List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= LIMIT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    /**
     * Gets top 5 playlists.
     *
     * @return the top 5 playlists
     */
    public List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= LIMIT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    /**
     * gets the top 5 artists
     *
     * @return the artists
     */
    public List<String> getTop5Artists() {
        List<Artist> sortedArtists = new ArrayList<>(getArtists());
        sortedArtists.sort(Comparator.comparingInt(Artist::getArtistLikes)
                .reversed().
                thenComparing(Artist::getUsername, String.CASE_INSENSITIVE_ORDER));

        List<String> topArtists = new ArrayList<>();

        int count = 0;
        for (Artist artist : sortedArtists) {
            if (count >= LIMIT) {
                break;
            }
            topArtists.add(artist.getUsername());
            count++;
        }
        return topArtists;
    }

    /**
     * gets the online users
     *
     * @return online users
     */
    public List<String> getOnlineUsers() {
        List<String> onlineUsers = new ArrayList<>();
        for (User user : users) {
            user.getOnlineUsers(onlineUsers);
        }

        return onlineUsers;
    }

    /**
     * adds a user
     *
     * @param username
     * @param age
     * @param city
     * @param type
     * @return the message
     */
    public String addUser(final String username, final  Integer age,
                          final String city, final String type) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return "The username " + username + " is already taken.";
            }
        }
        for (Artist artist : artists) {
            if (artist.getUsername().equals(username)) {
                return "The username " + username + " is already taken.";
            }
        }
        for (Host host : hosts) {
            if (host.getUsername().equals(username)) {
                return "The username " + username + " is already taken.";
            }
        }

        if (type.equals("user")) {
            User user = new User(username, age, city);
            users.add(user);
            return "The username " + username + " has been added successfully.";
        }

        if (type.equals("artist")) {
            Artist artist = new Artist(username, age, city);
            artists.add(artist);
            return "The username " + username + " has been added successfully.";
        }

        if (type.equals("host")) {
            Host host = new Host(username, age, city);
            hosts.add(host);
            return "The username " + username + " has been added successfully.";
        }

        return  null;
    }

    /**
     * gets all users
     *
     * @return the users
     */
    public List<String> getAllUsers() {
        List<String> allUsers = new ArrayList<>();

        for (User user : users) {
            allUsers.add(user.getUsername());
        }
        for (Artist artist : artists) {
            allUsers.add(artist.getUsername());
        }
        for (Host host : hosts) {
            allUsers.add(host.getUsername());
        }

        return allUsers;
    }

    /**
     * deletes a user
     *
     * @param username
     * @return the string
     */
    public String deleteUser(final String username) {
        User user = Admin.getInstance().getUser(username);
        if (user != null) {
            for (User user1 : users) {
                for (Playlist playlist : user.getPlaylists()) {
                    for (Song song : playlist.getSongs()) {
                        if (user1.getPlayer().getSource() != null) {
                            if (user1.getPlayer().getSource().getAudioFile().equals(song)) {
                                return username + " can't be deleted.";
                            }
                        }
                    }
                }
            }
            for (Song song : user.getLikedSongs()) {
                song.dislike();
            }

            for (Playlist playlist : user.getFollowedPlaylists()) {
                playlist.decreaseFollowers();
            }


                for (Playlist playlist : getPlaylists()) {
                    if (playlist.matchesOwner(user.getUsername())) {
                        for (Song song : playlist.getSongs()) {
                            songs.remove(getSong(song.getName()));
                        }
                    }
                }


            //chatgpt
            for (User user1 : users) {
                user1.getFollowedPlaylists().removeIf(playlist ->
                        playlist.getOwner().equals(user.getUsername()));
            }
            //chatgpt
            users.remove(user);
            return username + " was successfully deleted.";
        }
        Artist artist = Admin.getInstance().getArtist(username);
        if (artist != null) {
            for (User user1 : users) {
                if (Admin.getInstance().getArtist(user1.getCurrentPage()) != null) {
                    return username + " can't be deleted.";
                }
            }
            for (User user1 : users) {
                if (user1.getPlayer().getSource() != null) {
                    if (user1.getPlayer().getSource().getAudioFile().matchesArtist(username)) {
                        return username + " can't be deleted.";
                    }
                }
            }
            for (User user1 : users) {
                if (user1.getPlayer().getSource() != null) {
                    if (user1.getPlayer().getSource().getType()
                            == Enums.PlayerSourceType.PLAYLIST) {
                        for (Song song1 : ((Playlist) user1.getPlayer()
                                .getSource().getAudioCollection()).getSongs()) {
                            if (song1.matchesArtist(artist.getUsername())) {
                                return username + " can't be deleted.";
                            }
                        }
                    }
                }
            }
            for (User user1 : users) {
                for (Playlist playlist : user1.getPlaylists()) {
                    for (Song song1 : playlist.getSongs()) {
                        for (Album album : artist.getAlbums()) {
                            for (Song song : album.getSongs()) {
                                if (song1.equals(song)) {
                                    playlist.removeSong(song1);
                                }
                            }
                        }
                    }
                }
            }
            for (Album album : artist.getAlbums()) {
                for (Song song : album.getSongs()) {
                    songs.remove(getSong(song.getName()));
                    for (User user1 : users) {
                        user1.getLikedSongs().remove(user1.getLikedSong(song.getName()));
                    }
                }
                albums.remove(album);
            }
            artists.remove(artist);
            return username + " was successfully deleted.";
        }
        Host host = Admin.getInstance().getHost(username);
        if (host != null) {
            for (User user1 : users) {
                if (Admin.getInstance().getHost(user1.getCurrentPage()) != null) {
                    return username + " can't be deleted.";
                }
            }
            for (User user1 : users) {
                if (user1.getPlayer().getSource() != null) {
                    if (user1.getPlayer().getSource().getAudioCollection().matchesOwner(username)) {
                        return username + " can't be deleted.";
                    }
                }
            }
            podcasts.removeIf(podcast -> podcast.matchesOwner(username));
            return username + " was successfully deleted.";
        }
        return "The username " + username + " doesn't exist.";
    }

    /**
     * gets top 5 albums
     *
     * @return the string of album names
     */
    public List<String> getTop5Albums() {
        List<Album> sortedAlbums = new ArrayList<>(getAlbums());
        sortedAlbums.sort(Comparator.comparingInt(Album::getAlbumLikes)
                .reversed().
                thenComparing(Album::getName, String.CASE_INSENSITIVE_ORDER));

        List<String> topAlbums = new ArrayList<>();

        int count = 0;
        for (Album album : sortedAlbums) {
            if (count >= LIMIT) {
                break;
            }
            topAlbums.add(album.getName());
            count++;
        }
        return topAlbums;
    }

    /**
     * Reset.
     */
    public void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        artists = new ArrayList<>();
        hosts = new ArrayList<>();
        albums = new ArrayList<>();
        timestamp = 0;
    }
}
