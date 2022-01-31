package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {

    private Map<String, Set<Movie>> shows = new TreeMap<>();


    public void readFromFile(Path path) {


        try (BufferedReader br = Files.newBufferedReader(path)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String[] anotherParts = parts[0].split("-");
                String theatreName = anotherParts[0];
                String title = anotherParts[1];
                LocalTime startTime = LocalTime.parse(parts[1]);

                shows.compute(theatreName, (k, v) -> v != null ? v : new TreeSet<>()).add(new Movie(title, startTime));
            }

            System.out.println(shows);


        } catch (IOException e) {
            throw new IllegalStateException("File not exist");
        }

    }

    public Map<String, Set<Movie>> getShows() {
        return new TreeMap<>(shows);
    }

    public List<String> findMovie(String title) {
        return shows.entrySet().stream()
                .filter(entry -> entry.getValue()
                        .stream()
                        .anyMatch(movie -> movie.getTitle().equals(title)))
                .map(Map.Entry::getKey)
                .toList();
    }

    public LocalTime findLatestShow(String title) {
        return null;
    }

}

