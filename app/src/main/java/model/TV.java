package model;

public class TV {
    private int id;
    private String title;
    private String rating;
    private String genre;
    private String synopsis;
    private String releaseDate;
    private String image;

    public TV(int id, String title, String rating, String genre, String synopsis, String releaseDate, String image) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.genre = genre;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
