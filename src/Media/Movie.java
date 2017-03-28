package Media;


public class Movie extends Media {

        private float length;

        public float getLength() {
            return length;
        }

        public Movie(String title, int mid, int cid, float avg_rating, float length) {
            super(title, mid, cid, avg_rating);
            this.length = length;
        }
}