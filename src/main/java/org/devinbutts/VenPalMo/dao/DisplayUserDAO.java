package org.devinbutts.VenPalMo.dao;

import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DisplayUserDAO extends JpaRepository<UserDTO, Integer> {

    @Override
    <S extends UserDTO> List<S> findAll(Example<S> example);

    @Override
    Optional<UserDTO> findById(Integer integer);

    @Query("SELECT u FROM UserDTO u WHERE u.firstName like :firstName AND u.lastName like :lastName AND u.email like :email")
    public List<UserDTO> findByFirstLastEmail(String firstName, String lastName, String email);




}
