package app;

import app.audio.Collections.AlbumOutput;
import app.audio.Collections.PlaylistOutput;
import app.audio.Collections.PodcastOutput;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Command runner.
 */
public final class CommandRunner {
    /**
     * The Object mapper.
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    private CommandRunner() {
    }

    /**
     * Search object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode search(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Filters filters = new Filters(commandInput.getFilters());
        String type = commandInput.getType();

        if (user == null) {
            return null;
        }
        ArrayList<String> results = user.search(filters, type);
        String message;
        if (results.isEmpty()) {
            message = user.searchMessage();
        } else {
            message = "Search returned " + results.size() + " results";
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        objectNode.put("results", objectMapper.valueToTree(results));

        return objectNode;
    }

    /**
     * Select object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode select(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        if (user == null) {
            return null;
        }
        String message = user.select(commandInput.getItemNumber());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Load object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode load(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        if (user == null) {
            return null;
        }
        String message = user.load();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Play pause object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String message = user.playPause();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Repeat object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        if (user == null) {
            return null;
        }
        String message = user.repeat();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Shuffle object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Integer seed = commandInput.getSeed();
        String message = user.shuffle(seed);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Forward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        if (user == null) {
            return null;
        }
        String message = user.forward();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Backward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String message = user.backward();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Like object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode like(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        if (user == null) {
            return null;
        }
        String message = user.like();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Next object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode next(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String message = user.next();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Prev object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String message = user.prev();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Create playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        if (user == null) {
            return null;
        }
        String message = user.createPlaylist(commandInput.getPlaylistName(),
                                             commandInput.getTimestamp());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add remove in playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        if (user == null) {
            return null;
        }
        String message = user.addRemoveInPlaylist(commandInput.getPlaylistId());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Switch visibility object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String message = user.switchPlaylistVisibility(commandInput.getPlaylistId());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Show playlists object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        ArrayList<PlaylistOutput> playlists = user.showPlaylists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Follow object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        if (user == null) {
            return null;
        }
        String message = user.follow();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Status object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode status(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());

        if (user == null) {
            return null;
        }
        PlayerStats stats = user.getPlayerStats();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("stats", objectMapper.valueToTree(stats));

        return objectNode;
    }

    /**
     * Show liked songs object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        ArrayList<String> songs = user.showPreferredSongs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets preferred genre.
     *
     * @param commandInput the command input
     * @return the preferred genre
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String preferredGenre = user.getPreferredGenre();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * Gets top 5 songs.
     *
     * @param commandInput the command input
     * @return the top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = Admin.getInstance().getTop5Songs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets top 5 playlists.
     *
     * @param commandInput the command input
     * @return the top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        List<String> playlists = Admin.getInstance().getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Switch visibility object node.
     *
     * @param commandInput the command input
     * @return the message
     */
    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String message;

        if (user == null) {
            Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
            if (artist == null) {
                Host host = Admin.getInstance().getHost(commandInput.getUsername());
                if (host == null) {
                    message = "The username " + commandInput.getUsername() + " doesn't exist.";
                } else {
                    message = commandInput.getUsername() + " is not a normal user.";
                }
            } else {
                message = commandInput.getUsername() + " is not a normal user.";
            }
        } else {
            message = user.switchConnectionStatus();
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Gets online users.
     *
     * @param commandInput the command input
     * @return the online users
     */
    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        List<String> onlineUsers = Admin.getInstance().getOnlineUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(onlineUsers));

        return objectNode;
    }


    /**
     * adds an user
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode addUser(final CommandInput commandInput) {
        String message = Admin.getInstance().addUser(commandInput.getUsername(),
                commandInput.getAge(), commandInput.getCity(), commandInput.getType());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * adds an album
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode addAlbum(final CommandInput commandInput) {
        String message;

        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        if (artist == null) {
            User user = Admin.getInstance().getUser(commandInput.getUsername());
            if (user == null) {
                Host host = Admin.getInstance().getHost(commandInput.getUsername());
                if (host == null) {
                    message = "The username " + commandInput.getUsername() + " doesn't exist.";
                } else {
                    message = commandInput.getUsername() + " is not an artist.";
                }
            } else {
                message = commandInput.getUsername() + " is not an artist.";
            }
        } else {
            message = artist.addAlbum(commandInput.getUsername(),
                    commandInput.getName(), commandInput.getSongs(),
                    commandInput.getDescription(), commandInput.getReleaseYear());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *show all the albums
     *
     * @param commandInput
     * @return the albums
     */
    public static ObjectNode showAlbums(final CommandInput commandInput) {
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        ArrayList<AlbumOutput> albums = artist.showAlbums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * prints the current page
     *
     * @param commandInput
     * @return the page
     */
    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        String message = user.printCurrentPage();


        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * adds an event
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode addEvent(final CommandInput commandInput) {
        String message;

        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        if (artist == null) {
            User user = Admin.getInstance().getUser(commandInput.getUsername());
            if (user == null) {
                Host host = Admin.getInstance().getHost(commandInput.getUsername());
                if (host == null) {
                    message = "The username " + commandInput.getUsername() + " doesn't exist.";
                } else {
                    message = commandInput.getUsername() + " is not an artist.";
                }
            } else {
                message = commandInput.getUsername() + " is not an artist.";
            }
        } else {
            message = artist.addEvent(commandInput.getUsername(),
                    commandInput.getName(), commandInput.getDescription(),
                    commandInput.getDate());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * adds a merch
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode addMerch(final CommandInput commandInput) {
        String message;

        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        if (artist == null) {
            User user = Admin.getInstance().getUser(commandInput.getUsername());
            if (user == null) {
                Host host = Admin.getInstance().getHost(commandInput.getUsername());
                if (host == null) {
                    message = "The username " + commandInput.getUsername() + " doesn't exist.";
                } else {
                    message = commandInput.getUsername() + " is not an artist.";
                }
            } else {
                message = commandInput.getUsername() + " is not an artist.";
            }
        } else {
            message = artist.addMerch(commandInput.getUsername(),
                    commandInput.getName(), commandInput.getDescription(),
                    commandInput.getPrice());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * gets all users
     *
     * @param commandInput
     * @return the users
     */
    public static ObjectNode getAllUsers(final CommandInput commandInput) {
        List<String> allUsers = Admin.getInstance().getAllUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(allUsers));

        return objectNode;
    }

    /**
     * deletes a user
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode deleteUser(final CommandInput commandInput) {
        String message = Admin.getInstance().deleteUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * adds a podcast
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode addPodcast(final CommandInput commandInput) {
        String message;

        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        if (host == null) {
            User user = Admin.getInstance().getUser(commandInput.getUsername());
            if (user == null) {
                Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
                if (artist == null) {
                    message = "The username " + commandInput.getUsername() + " doesn't exist.";
                } else {
                    message = commandInput.getUsername() + " is not a host.";
                }
            } else {
                message = commandInput.getUsername() + " is not a host.";
            }
        } else {
            message = host.addPodcast(commandInput.getUsername(),
                    commandInput.getName(), commandInput.getEpisodes());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * adds an announcement
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode addAnnouncement(final CommandInput commandInput) {
        String message;

        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        if (host == null) {
            User user = Admin.getInstance().getUser(commandInput.getUsername());
            if (user == null) {
                Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
                if (artist == null) {
                    message = "The username " + commandInput.getUsername() + " doesn't exist.";
                } else {
                    message = commandInput.getUsername() + " is not a host.";
                }
            } else {
                message = commandInput.getUsername() + " is not a host.";
            }
        } else {
            message = host.addAnnouncement(commandInput.getUsername(),
                    commandInput.getName(), commandInput.getDescription());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * shows all the podcasts
     *
     * @param commandInput
     * @return the podcasts
     */
    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        ArrayList<PodcastOutput> podcasts = host.showPodcasts();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(podcasts));

        return objectNode;
    }

    /**
     * gets top 5 albums
     *
     * @param commandInput
     * @return the albums
     */
    public static ObjectNode getTop5Albums(final CommandInput commandInput) {
        List<String> albums = Admin.getInstance().getTop5Albums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * removes an announcement
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode removeAnnouncement(final CommandInput commandInput) {
        String message;

        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        if (host == null) {
            User user = Admin.getInstance().getUser(commandInput.getUsername());
            if (user == null) {
                Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
                if (artist == null) {
                    message = "The username " + commandInput.getUsername() + " doesn't exist.";
                } else {
                    message = commandInput.getUsername() + " is not a host.";
                }
            } else {
                message = commandInput.getUsername() + " is not a host.";
            }
        } else {
            message = host.removeAnnouncement(commandInput.getUsername(),
                    commandInput.getName());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * removes an album
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode removeAlbum(final CommandInput commandInput) {
        String message;

        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        if (artist == null) {
            User user = Admin.getInstance().getUser(commandInput.getUsername());
            if (user == null) {
                Host host = Admin.getInstance().getHost(commandInput.getUsername());
                if (host == null) {
                    message = "The username " + commandInput.getUsername() + " doesn't exist.";
                } else {
                    message = commandInput.getUsername() + " is not an artist.";
                }
            } else {
                message = commandInput.getUsername() + " is not an artist.";
            }
        } else {
            message = artist.removeAlbum(commandInput.getUsername(),
                    commandInput.getName());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * changes the current page
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode changePage(final CommandInput commandInput) {
        String message;

        User user = Admin.getInstance().getUser(commandInput.getUsername());
        message = user.changePage(commandInput.getUsername(), commandInput.getNextPage());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * removes a podcast
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode removePodcast(final CommandInput commandInput) {
        String message;

        Host host = Admin.getInstance().getHost(commandInput.getUsername());
        if (host == null) {
            User user = Admin.getInstance().getUser(commandInput.getUsername());
            if (user == null) {
                Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
                if (artist == null) {
                    message = "The username " + commandInput.getUsername() + " doesn't exist.";
                } else {
                    message = commandInput.getUsername() + " is not a host.";
                }
            } else {
                message = commandInput.getUsername() + " is not a host.";
            }
        } else {
            message = host.removePodcast(commandInput.getUsername(),
                    commandInput.getName());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * deletes an event
     *
     * @param commandInput
     * @return the message
     */
    public static ObjectNode deleteEvent(final CommandInput commandInput) {
        String message;

        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        if (artist == null) {
            User user = Admin.getInstance().getUser(commandInput.getUsername());
            if (user == null) {
                Host host = Admin.getInstance().getHost(commandInput.getUsername());
                if (host == null) {
                    message = "The username " + commandInput.getUsername() + " doesn't exist.";
                } else {
                    message = commandInput.getUsername() + " is not an artist.";
                }
            } else {
                message = commandInput.getUsername() + " is not an artist.";
            }
        } else {
            message = artist.removeEvent(commandInput.getUsername(),
                    commandInput.getName());
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * gets top 5 artists
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode getTop5Artists(final CommandInput commandInput) {
        List<String> artists = Admin.getInstance().getTop5Artists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(artists));

        return objectNode;
    }
}
