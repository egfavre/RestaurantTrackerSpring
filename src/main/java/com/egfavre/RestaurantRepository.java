package com.egfavre;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by user on 6/21/16.
 */
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    public Iterable<Restaurant> findByRating(int rating);
    public Iterable<Restaurant> findByLocation(String location);
    public Iterable<Restaurant> findByRatingAndLocation(int rating, String location);
    public Iterable<Restaurant> findByRatingGreaterThanEqual(int rating);

    public int countByLocation(String location);
    public Iterable<Restaurant> findByOrderByRatingDesc();

    @Query("SELECT r FROM Restaurant r WHERE r.location LIKE ?1%")
    public Iterable<Restaurant> searchLocation(String location);

    public Iterable<Restaurant> findByUser(User user);
}
