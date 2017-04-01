package Results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Query.Query;

public class Results {

    private String creator;
    private String mediaTitle;
    private float rating;
    private String category;
    private int id;

    public Results(String creator, String mediaTitle, float rating, String category, int id) {
        this.creator = creator;
        this.mediaTitle = mediaTitle;
        this.rating = rating;
        this.category = category;
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public float getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public int getId() { return id; }

}
