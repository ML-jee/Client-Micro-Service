package org.example.clientmicroservice.repositories;

import org.example.clientmicroservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface UserRepository extends JpaRepository <User,String> {

    Optional<User> findByAdresseWallet(String adresseWallet);

    Optional<User> findByCin(String cin);



}
