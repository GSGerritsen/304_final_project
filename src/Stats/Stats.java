package Stats;


public class Stats {

    private int cid;
    private String name;
    private float rating;

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public Stats(int cid, String name, float rating) {
        this.cid = cid;
        this.name = name;
        this.rating = rating;
    }
}
