package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import fileio.input.EpisodeInput;

import java.util.ArrayList;
import java.util.List;

public final class Podcast extends AudioCollection {
    private List<Episode> episodes = new ArrayList<>();

    /**
     * Instantiates a podcast.
     *
     * @param name
     * @param owner
     * @param episodes
     */
    public Podcast(final String name, final String owner, final List<Episode> episodes) {
        super(name, owner);
        this.episodes = episodes;
    }

    /**
     * Instantiates a podcast.
     *
     * @param name
     * @param owner
     * @param episodes
     */
    public Podcast(final String name, final String owner, final ArrayList<EpisodeInput> episodes) {
        super(name, owner);
        for (EpisodeInput episodeInput : episodes) {
            this.episodes.add(new Episode(episodeInput.getName(),
                    episodeInput.getDuration(), episodeInput.getDescription()));
        }
    }

    /**
     * gets the episodes list
     *
     * @return the episodes list
     */
    public List<Episode> getEpisodes() {
        return episodes;
    }

    /**
     * gets the number of episodes of a podcast
     *
     * @return the size of episodes
     */
    @Override
    public int getNumberOfTracks() {
        return episodes.size();
    }

    /**
     * gets an episode bt index
     *
     * @param index the index
     * @return a episode
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return episodes.get(index);
    }
}
