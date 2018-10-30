package com.hackathon.nf.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.nf.model.User;

@Repository
@Transactional
public interface UsserDao extends CrudRepository<User, Long> {

    long countByUserName(String userName);

    <S extends User> S save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void deleteById(long id);
}
