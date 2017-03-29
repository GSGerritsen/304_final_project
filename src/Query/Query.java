package Query;

public class Query {

    private String search;
    private Boolean book;
    private Boolean comic;
    private Boolean movie;
    private Boolean tv;
    private Boolean creator;
    private Boolean mediaTitle;
    private Boolean max;
    private Boolean min;
    private Boolean avgMax;
    private Boolean avgMin;

    public Query(String search, Boolean book, Boolean comic, Boolean movie, Boolean tv, Boolean creator, Boolean mediaTitle, Boolean max, Boolean min, Boolean avgMax, Boolean avgMin) {
        this.search = search;
        this.book = book;
        this.comic = comic;
        this.movie = movie;
        this.tv = tv;
        this.creator = creator;
        this.mediaTitle = mediaTitle;
        this.max = max;
        this.min = min;
        this.avgMax = avgMax;
        this.avgMin = avgMin;
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

    public Boolean getCreator() {
        return creator;
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

    public Boolean getAvgMax() {
        return avgMax;
    }

    public Boolean getAvgMin() {
        return avgMin;
    }


}
