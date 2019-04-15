package com.gmail.morovo1988.minURL.repository;

import com.gmail.morovo1988.minURL.model.MiniURL;
import com.gmail.morovo1988.minURL.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StatisticReposetory extends JpaRepository<Statistic, Long> {
    List<Statistic> findAllByMiniURLId(Long id);

    @Query("SELECT browser, count(browser) FROM Statistic where miniURL = :adress group by browser")
    Map<String,Long> findCountConversionByBrowser(@Param("adress")MiniURL miniURL);

//    @Query("SELECT browser FROM Statistic s where s.miniURL = :number group by browser")
//    List<String> findCountConversionByBrowser2(@Param("number") MiniURL id);
}
