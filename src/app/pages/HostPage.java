package app.pages;

import app.audio.Collections.Podcast;
import app.audio.Files.Announcement;

import java.util.ArrayList;

public class HostPage implements Page {
    private ArrayList<Podcast> podcasts;
    private ArrayList<Announcement> announcements;

    public HostPage(final ArrayList<Podcast> podcasts,
                    final ArrayList<Announcement> announcements) {
        this.podcasts = podcasts;
        this.announcements = announcements;
    }

    /**
     * prints the current page
     *
     * @return
     */
    @Override
    public String printCurrentPage() {
        String message = "Podcasts:\n\t[";
        String aux = "";

        int size = podcasts.size();
        int count = 0;
        while (size != 0) {
            aux += podcasts.get(count).getName();
            aux += ":\n\t[";

            int size1 = podcasts.get(count).getEpisodes().size();
            int count1 = 0;
            while (size1 != 0) {
                aux += podcasts.get(count).getEpisodes().get(count1).getName();
                aux += " - ";
                aux += podcasts.get(count).getEpisodes().get(count1).getDescription();

                if (size1 - 1 == 0) {
                    break;
                }
                aux += ", ";

                count1++;
                size1--;
            }
            aux += "]\n";

            if (size - 1 == 0) {
                break;
            }
            aux += ", ";

            count++;
            size--;
        }

        message += aux;
        message += "]\n\nAnnouncements:\n\t[";

        aux = "";

        size = announcements.size();
        count = 0;
        while (size != 0) {
            aux += announcements.get(count).getName();
            aux += ":\n\t";
            aux += announcements.get(count).getDescription();
            if (size - 1 == 0) {
                break;
            }
            aux += ", ";

            count++;
            size--;
        }

        message += aux;
        message += "\n]";

        return message;
    }
}
