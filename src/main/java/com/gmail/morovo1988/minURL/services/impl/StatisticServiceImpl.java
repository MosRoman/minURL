package com.gmail.morovo1988.minURL.services.impl;

import com.gmail.morovo1988.minURL.model.MiniURL;
import com.gmail.morovo1988.minURL.model.Statistic;
import com.gmail.morovo1988.minURL.repository.StatisticReposetory;
import com.gmail.morovo1988.minURL.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
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
    public Map<String, Long> findAllStatisticByMiniURLIdAndBrowser(MiniURL miniURL) {

        Map<String, Long> map = new HashMap<>();
        List<String> browsers = this.statisticReposetory.findAllBrowserByMiniURL(miniURL);
        for (String b : browsers) {
            Long count = this.statisticReposetory.countConversionByBrowser(miniURL, b);
            map.put(b, count);
        }
        return map;
    }


    @Override
    public Map<String, Long> findAllStatisticByMiniURLdAndReferer(MiniURL miniURL) {

        Map<String, Long> map = new HashMap<>();
        List<String> referers = this.statisticReposetory.findAllRefererByMiniURL(miniURL);
        for (String b : referers) {
            Long count = this.statisticReposetory.countConversionByReferer(miniURL, b);
            map.put(b, count);
        }
        return map;
    }

    @Override
    public Map<LocalDate, Long> findAllStatisticByMiniURLIdAndDate(MiniURL miniURL) {
        Map<LocalDate, Long> map = new HashMap<>();
        List<LocalDate> dates = this.statisticReposetory.findAllDateByMiniURL(miniURL);
        for (LocalDate localDate : dates) {
            Long count = this.statisticReposetory.countConversionByDate(miniURL, localDate);
            map.put(localDate, count);
        }
        return map;
    }
}
