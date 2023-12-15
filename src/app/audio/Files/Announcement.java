package app.audio.Files;

import lombok.Getter;

@Getter
public class Announcement {
    private String name;
    private String description;

    public Announcement(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
}
