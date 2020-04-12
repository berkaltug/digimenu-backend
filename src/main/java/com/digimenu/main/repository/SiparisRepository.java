package com.digimenu.main.repository;

import com.digimenu.main.domain.entity.Siparis;
import com.digimenu.main.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiparisRepository extends JpaRepository<Siparis,Long> {
    List<Siparis> findAllByUser(User user);

}
