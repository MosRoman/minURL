package com.gmail.morovo1988.minURL.repository;

import com.gmail.morovo1988.minURL.model.MiniURL;
import com.gmail.morovo1988.minURL.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StatisticReposetory extends JpaRepository<Statistic, Long> {
    List<Statistic> findAllByMiniURLId(Long id);

    @Query("SELECT browser FROM Statistic s where s.miniURL = :miniURL group by browser")
    List<String> findAllBrowserByMiniURL(@Param("miniURL") MiniURL miniURL);

    @Query("SELECT  count(browser) FROM Statistic where miniURL = :miniURL and browser = :browser group by browser")
    Long countConversionByBrowser(@Param("miniURL") MiniURL miniURL, @Param("browser") String browser);

    @Query("SELECT referer FROM Statistic s where s.miniURL = :miniURL group by browser")
    List<String> findAllRefererByMiniURL(@Param("miniURL") MiniURL miniURL);

    @Query("SELECT  count(referer) FROM Statistic where miniURL = :miniURL and referer = :referer group by referer")
    Long countConversionByReferer(@Param("miniURL") MiniURL miniURL, @Param("referer") String referer);

    @Query("SELECT date FROM Statistic s where s.miniURL = :miniURL group by browser")
    List<LocalDate> findAllDateByMiniURL(@Param("miniURL") MiniURL miniURL);

    @Query("SELECT  count(date) FROM Statistic where miniURL = :miniURL and date = :date group by date")
    Long countConversionByDate(@Param("miniURL") MiniURL miniURL, @Param("date") LocalDate localDateTime);

}
