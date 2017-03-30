package Media;


public class Comic extends Media{

    private int num_pages;

    public int getLength() {
        return num_pages;
    }

    public Comic(String title, int mid, int cid, float avg_rating, int num_pages) {
        super(title, mid, cid, avg_rating);
        this.num_pages = num_pages;
    }
}
