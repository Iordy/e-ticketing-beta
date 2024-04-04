package classes;
import java.sql.Date;

public final class MusicalEvent extends Event{

    private String bandName;
    private String musicGenre;
    private int numberOfSongs;

    public MusicalEvent(String eventName, Date eventDate, String eventLocation, double BasicTicketPrice, int numberOfTickets, String bandName, String musicGenre, int numberOfSongs) {
        super(eventName, eventDate, eventLocation, BasicTicketPrice, numberOfTickets);
        this.bandName = bandName;
        this.musicGenre = musicGenre;
        this.numberOfSongs = numberOfSongs;
    }

    public String getBandName() {
        return bandName;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }
    
}
