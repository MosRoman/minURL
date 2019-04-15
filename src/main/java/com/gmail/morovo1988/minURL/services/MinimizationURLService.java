package com.gmail.morovo1988.minURL.services;

import com.gmail.morovo1988.minURL.model.MiniURL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MinimizationURLService {
    Page<MiniURL> findAll(Pageable pageable);

    MiniURL create(MiniURL req);

    MiniURL findById(Long id);

    MiniURL updateURL(MiniURL miniURL);

    void delete(MiniURL miniURL);
}
