package org.devinbutts.VenPalMo.dao;

import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DisplayUserDAO extends JpaRepository<UserDTO, Integer> {

    @Override
    <S extends UserDTO> List<S> findAll(Example<S> example);

    @Query("Select u FROM UserDTO u WHERE u.id = :id")
    public UserDTO findByUserId(Integer id);

    @Query("SELECT u FROM UserDTO u WHERE u.firstName like :firstName AND u.lastName like :lastName AND u.email like :email AND u.email NOT LIKE :LoggedInUserEmail")
    public List<UserDTO> findByFirstLastEmail(String firstName, String lastName, String email, String LoggedInUserEmail);

    @Query("SELECT u FROM UserDTO u WHERE u.email like :email")
    public UserDTO findUserByEmail(String email);


}
