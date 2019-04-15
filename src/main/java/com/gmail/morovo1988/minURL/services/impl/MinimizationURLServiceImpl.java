package com.gmail.morovo1988.minURL.services.impl;

import com.gmail.morovo1988.minURL.model.MiniURL;
import com.gmail.morovo1988.minURL.repository.MiniURLReposetory;
import com.gmail.morovo1988.minURL.services.MinimizationURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MinimizationURLServiceImpl implements MinimizationURLService {
    @Autowired
    private MiniURLReposetory miniURLReposetory;

    @Override
    public Page<MiniURL> findAll(Pageable pageable) {
        return this.miniURLReposetory.findAll(pageable);
    }

    @Override
    public MiniURL create(MiniURL req) {
        return this.miniURLReposetory.save(req);
    }

    @Override
    public MiniURL findById(Long id) {
        return this.miniURLReposetory.getOne(id);
    }

    @Override
    public MiniURL updateURL(MiniURL miniURL) {
        final MiniURL miniAdress = this.miniURLReposetory
                .findById(miniURL.getId())
                .map(u -> {
                    u.setLongAdress(miniURL.getLongAdress());
                    u.setDayDestroy(miniURL.getDayDestroy());
                    return u;
                }).orElseThrow(RuntimeException::new);

       return this.miniURLReposetory.save(miniAdress);
    }


    @Override
    public void delete(MiniURL miniURL) {
        this.miniURLReposetory.delete(miniURL);
    }
}
