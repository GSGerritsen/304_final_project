package Results;

/**
 * Created by gerritgerritsen on 2017-03-28.
 */
public class Results {

    private String creator;
    private String category;
    private String mediaTitle;
    private int rating;

    public Results(String creator, String category, String mediaTitle, int rating) {
        this.creator = creator;
        this.category = category;
        this.mediaTitle = mediaTitle;
        this.rating = rating;
    }

    public String getCreator() {
        return creator;
    }

    public String getCategory() {
        return category;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public int getRating() {
        return rating;
    }
}
