package com.hackathon.nf.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.nf.model.CategoryCount;
import com.hackathon.nf.model.EventSelection;

@Repository
@Transactional
public interface EventSelectionDao extends CrudRepository<EventSelection, Long> {

    <S extends EventSelection> S save(EventSelection eventSelection);

    List<EventSelection> findAll();

    Optional<EventSelection> findById(Long id);

    void deleteById(long id);

    @Query("SELECT new com.hackathon.nf.model.CategoryCount(count(movie), movie) FROM EventSelection GROUP BY movie")
    List<CategoryCount> findMaxSelectedMovie();

    @Query("SELECT new com.hackathon.nf.model.CategoryCount(count(restaurant), restaurant) FROM EventSelection GROUP BY restaurant")
    List<CategoryCount> findMaxSelectedRestaurant();

    @Query("SELECT new com.hackathon.nf.model.CategoryCount(count(drink), drink) FROM EventSelection GROUP BY drink")
    List<CategoryCount> findMaxSelectedDrink();
    
    @Query("SELECT es FROM EventSelection es where es.event.eventName = ?1 and es.user.userName = ?2")
    List<EventSelection> findAllUserAndEvent(String eventName, String userName);
}
