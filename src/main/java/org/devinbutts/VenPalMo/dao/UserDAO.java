package org.devinbutts.VenPalMo.dao;

import org.devinbutts.VenPalMo.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    @Override
    <S extends User> List<S> findAll(Example<S> example);

    @Override
    Optional<User> findById(Integer integer);

    @Override
    <S extends User> S save(S entity);

    @Override
    void delete(User entity);

    @Query("SELECT u FROM User u WHERE u.email like :email" )
    User findByEmail(String email);


}
