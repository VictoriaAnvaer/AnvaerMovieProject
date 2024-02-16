import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    ArrayList<Movie> movieList;
    Scanner scanner;

    MovieCollection() {
        movieList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        makeList();
        menu();

    }
    public void menu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();
            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public void searchTitles() {
        System.out.print("Enter a title search term: ");
        String titleOption = scanner.nextLine();
        ArrayList<Movie> titleSearch = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            if ((movieList.get(i).getTitle()).toLowerCase().indexOf(titleOption) != -1) {
                titleSearch.add(movieList.get(i));
            }
        }
        if (titleSearch.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            for (int i = 1; i < titleSearch.size(); i++) {
                int compare = i - 1;
                Movie temp = titleSearch.get(i);
                while ((compare >=0) && (temp.getTitle().compareTo(titleSearch.get(compare).getTitle()) < 0)) {
                    titleSearch.set(compare + 1, titleSearch.get(compare));
                    titleSearch.set(compare, temp);
                    compare--;
                }
            }
            int count = 1;
            for (Movie m : titleSearch) {
                System.out.println(count + ". " + m.getTitle());
                count++;
            }
            System.out.println("Which movie would you like to learn more about?");
            System.out.print("Enter number: ");
            int movieOption = scanner.nextInt();
            titleSearch.get(movieOption - 1).movieInfo();
            scanner.nextLine();
        }
    }

    public void searchCast() {
        System.out.print("Enter a person to search for (first or last name): ");
        String nameOption = scanner.nextLine();
        ArrayList<String> actorSearch = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            if ((movieList.get(i).getCast()).toLowerCase().indexOf(nameOption) != -1) {
                String actors = movieList.get(i).getCast();
                String[] actorList = actors.split("\\|");
                for (int idx = 0; idx < actorList.length; idx++) {
                    if (actorList[idx].toLowerCase().indexOf(nameOption) != -1) {
                        actorSearch.add(actorList[idx]);
                    }
                }
            }
        }
        if (actorSearch.size() == 0) {
            System.out.println("No results match your search");
        } else {
            for (int i = 1; i < actorSearch.size(); i++) {
                int compare = i - 1;
                String temp = actorSearch.get(i);
                while ((compare >= 0) && (temp.compareTo(actorSearch.get(compare)) < 0)) {
                    actorSearch.set(compare + 1, actorSearch.get(compare));
                    actorSearch.set(compare, temp);
                    compare--;
                }
            }
            for (int i = 1; i < actorSearch.size(); i++) {
                if (actorSearch.get(i - 1).equals(actorSearch.get(i))) {
                    actorSearch.remove(i);
                    i--;
                }
            }
            int count = 1;
            for (String actor : actorSearch) {
                System.out.println(count + ". " + actor);
                count++;
            }
            System.out.println("Which would you like to see all movies for?");
            System.out.print("Enter number: ");
            int movieOption = scanner.nextInt();
            String actor = actorSearch.get(movieOption - 1);
            ArrayList<Movie> movieActor = new ArrayList<>();
            for (int i = 0; i < movieList.size(); i++) {
                if (movieList.get(i).getCast().indexOf(actor) != -1) {
                    movieActor.add(movieList.get(i));
                }
            }
            for (int i = 1; i < movieActor.size(); i++) {
                int compare = i - 1;
                Movie temp = movieActor.get(i);
                while ((compare >=0) && (temp.getTitle().compareTo(movieActor.get(compare).getTitle()) < 0)) {
                    movieActor.set(compare + 1, movieActor.get(compare));
                    movieActor.set(compare, temp);
                    compare--;
                }
            }
            count = 1;
            for (Movie m : movieActor) {
                System.out.println(count + ". " + m.getTitle());
                count++;
            }
            System.out.println("Which movie would you like to learn more about?");
            System.out.print("Enter number: ");
            movieOption = scanner.nextInt();
            movieActor.get(movieOption - 1).movieInfo();
            scanner.nextLine();
        }
    }

    public void makeList() {
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                // title,cast,director,overview,runtime,userRating
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String cast = splitData[1];
                String director = splitData[2];
                String overview = splitData[3];
                int runTime = Integer.parseInt(splitData[4]);
                double userRating = Double.parseDouble(splitData[5]);
                Movie m = new Movie(title, cast, director, overview, runTime, userRating);
                movieList.add(m);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
