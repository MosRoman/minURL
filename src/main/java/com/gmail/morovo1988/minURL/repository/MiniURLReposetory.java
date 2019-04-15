package com.gmail.morovo1988.minURL.repository;

import com.gmail.morovo1988.minURL.model.MiniURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiniURLReposetory extends JpaRepository<MiniURL, Long> {

}
