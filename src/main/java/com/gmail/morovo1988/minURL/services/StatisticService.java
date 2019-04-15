package com.gmail.morovo1988.minURL.services;

import com.gmail.morovo1988.minURL.model.MiniURL;
import com.gmail.morovo1988.minURL.model.Statistic;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatisticService {
    void create(Statistic statistic);

    List<Statistic> findAllStatisticByMiniURLId(Long id);

    Map<String,Long> findAllStatisticByMiniURLIdAndBrowser(MiniURL miniURL);

//    List<String> findAllStatisticByMiniURLIdAndBrowser2(MiniURL id);

    List<Statistic> findAllStatisticByMiniURLIdAndReferer(Long id, String referer);

    List<Statistic> findAllStatisticByMiniURLIdAndDate(Long id, LocalDate localDate);
}
