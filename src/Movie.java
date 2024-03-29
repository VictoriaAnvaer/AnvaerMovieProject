public class Movie {
    private String title;
    private String cast;
    private String director;
    private String overview;
    private int runTime;
    private double userRating;
    Movie(String title, String cast, String director, String overview, int runTime, double userRating) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.overview = overview;
        this.runTime = runTime;
        this.userRating = userRating;
    }
    public String getTitle() {
        return title;
    }
    public String getCast() {
        return cast;
    }
    public String getDirector() {
        return director;
    }
    public String getOverview() {
        return overview;
    }
    public int getRunTime() {
        return runTime;
    }
    public double getUserRating() {
        return userRating;
    }

    public void movieInfo() {
        System.out.println("Title: " + title);
        System.out.println("Runtime: " + runTime);
        System.out.println("Directed by: " + director);
        System.out.println("Cast: " + cast);
        System.out.println("Overview: " + overview);
        System.out.println("User rating: " + userRating);
    }
}
