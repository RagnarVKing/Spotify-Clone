package app.user;

import app.Admin;
import app.audio.Collections.Podcast;
import app.audio.Collections.PodcastOutput;
import app.audio.Files.Announcement;
import app.utils.Enums;
import fileio.input.EpisodeInput;
import lombok.Getter;

import java.util.ArrayList;

public class Host extends User {
    @Getter
    private ArrayList<Podcast> podcasts;
    @Getter
    private ArrayList<Announcement> announcements;
    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public Host(final String username, final int age, final String city) {
        super(username, age, city);
        this.podcasts = new ArrayList<>();
        this.announcements = new ArrayList<>();
    }

    /**
     * adds a podcast
     *
     * @param username
     * @param name
     * @param episodes
     * @return the string
     */
    public String addPodcast(final String username, final String name,
                             final ArrayList<EpisodeInput> episodes) {

        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(name)) {
                return username + " has another podcast with the same name.";
            }
        }
        for (int i = 0; i < episodes.size() - 1; i++) {
            for (int j = i + 1; j < episodes.size(); j++) {
                if (episodes.get(i).getName().equals(episodes.get(j).getName())) {
                    return username + " has the same episode at least twice in this podcast.";
                }
            }
        }
        Podcast podcast = new Podcast(name, username, episodes);
        podcasts.add(podcast);
        Admin.getInstance().addPodcast(podcast);
        return username + " has added new podcast successfully.";
    }

    /**
     * removes a podcast
     *
     * @param username
     * @param name
     * @return the string
     */
    public String removePodcast(final String username, final String name) {

        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(name)) {
                for (User user1 : Admin.getInstance().getUsers()) {
                    if (user1.getPlayer().getSource() != null) {
                        if (user1.getPlayer().getSource().getType()
                                == Enums.PlayerSourceType.PODCAST) {
                            if (user1.getPlayer().getSource().
                                    getAudioCollection().matchesName(name)) {
                                return username + " can't delete this podcast.";
                            }
                        }
                    }
                }

                podcasts.remove(podcast);
                Admin.getInstance().getHost(username).getPodcasts().remove(podcast);
                return username + " deleted the podcast successfully.";
            }
        }

        return username + " doesn't have a podcast with the given name.";
    }

    /**
     * adds an announcement
     *
     * @param username
     * @param name
     * @param description
     * @return the string
     */
    public String addAnnouncement(final String username,
                                  final String name, final String description) {
        for (Announcement announcement : announcements) {
            if (announcement.getName().equals(name)) {
                return username + " has already added an announcement with this name.";
            }
        }

        announcements.add(new Announcement(name, description));
        return username + " has successfully added new announcement.";
    }

    /**
     * removes an announcement
     *
     * @param username
     * @param name
     * @return the string
     */
    public String removeAnnouncement(final String username, final String name) {
        for (Announcement announcement : announcements) {
            if (announcement.getName().equals(name)) {
                announcements.remove(announcement);
                return username + " has successfully deleted the announcement.";
            }
        }

        return username + " has no announcement with the given name.";
    }

    /**
     * shows all the podcasts
     *
     * @return an array of podcast names
     */
    public ArrayList<PodcastOutput> showPodcasts() {
        ArrayList<PodcastOutput> podcastOutputs = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            podcastOutputs.add(new PodcastOutput(podcast));
        }

        return podcastOutputs;
    }
}
