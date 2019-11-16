package gamestore.models;

import gamestore.exceptions.DateIsTooLate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public class Game {
    private String title;
    private LocalDate release_date;
    private Collection<Platform> platforms;
    private Collection<Category> categories;
    private Rating rating;
    private UUID id;

    public Game() {
        this.id = UUID.randomUUID();
    }

    public Game(String title, LocalDate release_date, Collection<Platform> platforms, Collection<Category> categories, Rating rating) {
        this();
        this.title = title;
        this.release_date = release_date;
        this.platforms = platforms;
        this.categories = categories;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) throws DateIsTooLate {
        if (!release_date.isBefore(LocalDate.now().plusYears(1))) {
            throw new DateIsTooLate(release_date.toString());
        }
        this.release_date = release_date;
    }

    public Collection<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Collection<Platform> platforms) {
        this.platforms = platforms;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
