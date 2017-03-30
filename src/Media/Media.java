package Media;

public class Media {

    private String title;
    private int mid;
    private int cid;
    private float avg_rating;

    public String getTitle() {
        return title;
    }

    public int getMid() {
        return mid;
    }

    public int getCid() { return cid; }

    public float getAvgRating() {
        return avg_rating;
    }

    public Media(String title, int mid, int cid, float avg_rating) {
        this.title = title;
        this.mid = mid;
        this.cid = cid;
        this.avg_rating = avg_rating;
    }

}