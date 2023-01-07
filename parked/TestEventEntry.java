package afc.diarymanager.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import afc.diarymanager.model.EventEntry;


public class TestEventEntry {

    public static final String TITLE = "Roller coaster";
    public static final String DESC = "Saw Maverick at Cedar Point";
    EventEntry eventEntry;
    Image image;

    @BeforeEach
    void setup() {
        try {
            image = ImageIO.read(new File("./res/images/MaverickImg.jpg"));
        } catch (IOException e) {
            fail("Failed to read image");
        }
        eventEntry = new EventEntry(TITLE, DESC, image);
    }

    @Test
    void testConstructor() {
        assertEquals(TITLE, eventEntry.getTitle());
        assertEquals(DESC, eventEntry.getDesc());
        assertEquals(image, eventEntry.getImage());
    }

}
