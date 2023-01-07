package afc.diarymanager.model;

import android.media.Image;

public class EventEntry extends Entry{

    private Image image;
    public EventEntry(String title, String desc, Image image) {
        super(title, desc);
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
