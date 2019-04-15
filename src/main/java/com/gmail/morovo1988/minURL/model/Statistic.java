package com.gmail.morovo1988.minURL.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Statistic")
public class Statistic {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    private LocalDate date;

    private LocalTime time;

    private String referer;

    private String ip;

    private String browser;

    @ManyToOne
    @JoinColumn(name = "MiniUrl_id")
    private MiniURL miniURL;

    public Statistic() {
    }

    public Statistic(LocalDate date, LocalTime time, String referer, String ip, String browser, MiniURL miniURL) {
        this.date = date;
        this.time = time;
        this.referer = referer;
        this.ip = ip;
        this.browser = browser;
        this.miniURL = miniURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public MiniURL getMiniURL() {
        return miniURL;
    }

    public void setMiniURL(MiniURL miniURL) {
        this.miniURL = miniURL;
    }
}
