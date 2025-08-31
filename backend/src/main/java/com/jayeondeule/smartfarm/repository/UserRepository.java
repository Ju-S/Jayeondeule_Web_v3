package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);

    boolean existsByUserId(String userId);

    Page<User> findAllByFarmId(long farmId, Pageable pageable);
}
