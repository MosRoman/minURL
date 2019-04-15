package com.gmail.morovo1988.minURL.services.impl;

import com.gmail.morovo1988.minURL.model.MiniURL;
import com.gmail.morovo1988.minURL.model.Statistic;
import com.gmail.morovo1988.minURL.repository.StatisticReposetory;
import com.gmail.morovo1988.minURL.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticReposetory statisticReposetory;

    @Override
    public void create(Statistic statistic) {
        this.statisticReposetory.save(statistic);
    }

    @Override
    public List<Statistic> findAllStatisticByMiniURLId(Long id) {
        return this.statisticReposetory.findAllByMiniURLId(id);
    }

    @Override
    public Map<String,Long> findAllStatisticByMiniURLIdAndBrowser(MiniURL miniURL) {
        return this.statisticReposetory.findCountConversionByBrowser(miniURL);
    }

//    @Override
//    public List<String> findAllStatisticByMiniURLIdAndBrowser2(MiniURL id) {
//        return this.statisticReposetory.findCountConversionByBrowser2(id);
//    }

    @Override
    public List<Statistic> findAllStatisticByMiniURLIdAndReferer(Long id, String referer) {
        return null;
    }

    @Override
    public List<Statistic> findAllStatisticByMiniURLIdAndDate(Long id, LocalDate localDate) {
        return null;
    }
}
