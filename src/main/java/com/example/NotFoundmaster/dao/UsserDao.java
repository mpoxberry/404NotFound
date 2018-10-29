package com.example.NotFoundmaster.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.NotFoundmaster.model.User;

@Repository
public interface UsserDao extends CrudRepository<User, Long> {

    long countByUserName(String userName);

    <S extends User> S save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void deleteById(long id);
}
