package de.mainPackage.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.mainPackage.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
