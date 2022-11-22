package org.devinbutts.VenPalMo.dao;

import org.devinbutts.VenPalMo.model.DisplayUser;
import org.devinbutts.VenPalMo.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Repository
public interface DisplayUserDAO extends JpaRepository<DisplayUser, Integer> {

    @Override
    <S extends DisplayUser> List<S> findAll(Example<S> example);

    @Override
    Optional<DisplayUser> findById(Integer integer);




}
