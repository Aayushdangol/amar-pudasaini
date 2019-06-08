package model;

public class Movie {
    private String title;
    private String rating;
    private String genre;
    private String date;
    private String poster;

    public Movie(String title, String rating, String genre, String date, String poster) {
        this.title = title;
        this.rating = rating;
        this.genre = genre;
        this.date = date;
        this.poster = poster;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
