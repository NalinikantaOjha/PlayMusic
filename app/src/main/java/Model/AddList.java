package Model;

public class AddList {
    String url;
    String SongName;
    public AddList(){

    }

    public AddList(String url, String songName) {
        this.url = url;
        SongName = songName;
    }

    public String getUrl() {
        return url;
    }

    public String getSongName() {
        return SongName;
    }
}
