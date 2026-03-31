// Movie.java
// This class represents a movie with its basic details

public class Movie {

    private int movieId;
    private String title;
    private String genre;
    private int durationMinutes;
    private double rating;

    // Constructor to create a new movie
    public Movie(int movieId, String title, String genre, int durationMinutes, double rating) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
    }

    // Getters
    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getRating() {
        return rating;
    }

    // Display movie details in a nice format
    public void displayMovieInfo() {
        System.out.println("  ID      : " + movieId);
        System.out.println("  Title   : " + title);
        System.out.println("  Genre   : " + genre);
        System.out.println("  Duration: " + durationMinutes + " mins");
        System.out.println("  Rating  : " + rating + " / 10");
    }
}
