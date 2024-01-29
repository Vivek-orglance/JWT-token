package com.authtoken.authtoken.repository;

import com.authtoken.authtoken.entity.UserInfo;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String> {
    Optional<UserInfo> findByFullName(String username);
    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findById(String userId);





}

