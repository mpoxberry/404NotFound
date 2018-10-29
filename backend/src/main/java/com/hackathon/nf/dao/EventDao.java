package com.hackathon.nf.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.nf.model.Event;
import com.hackathon.nf.model.User;

@Repository
public interface EventDao extends CrudRepository<Event, Long> {

    <S extends User> S save(User user);

    List<Event> findAll();

    Optional<Event> findById(Long id);

    void deleteById(long id);
}
