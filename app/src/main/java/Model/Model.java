package Model;

import java.io.Serializable;

public class Model implements Serializable {
    String url;

    public Model(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
