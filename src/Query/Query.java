package Query;

public class Query {

    private String search;
    private Boolean book;
    private Boolean comic;
    private Boolean movie;
    private Boolean tv;
    private Boolean mediaTitle;
    private Boolean max;
    private Boolean min;


    public Query(String search, Boolean book, Boolean comic, Boolean movie, Boolean tv, Boolean mediaTitle, Boolean max, Boolean min) {
        this.search = search;
        this.book = book;
        this.comic = comic;
        this.movie = movie;
        this.tv = tv;
        this.mediaTitle = mediaTitle;
        this.max = max;
        this.min = min;

    }

    public String getSearch() {
        return search;
    }

    public Boolean getBook() {
        return book;
    }

    public Boolean getComic() {
        return comic;
    }

    public Boolean getMovie() {
        return movie;
    }

    public Boolean getTv() {
        return tv;
    }

    public Boolean getMediaTitle() {
        return mediaTitle;
    }

    public Boolean getMax() {
        return max;
    }

    public Boolean getMin() {
        return min;
    }



}
