package Media;


public class Show extends Media {
    private float length;

    public float getLength() {
        return length;
    }

    public Show(String title, int mid, int cid, float avg_rating, float length) {
        super(title, mid, cid, avg_rating);
        this.length = length;
    }
}
