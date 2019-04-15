package com.gmail.morovo1988.minURL.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Statistic")
public class Statistic {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    private LocalDateTime date;

    private String referer;

    private String ip;

    private String browser;

    @ManyToOne
    @JoinColumn(name = "MiniUrl_id")
    private MiniURL miniURL;

    public Statistic() {
    }

    public Statistic(LocalDateTime date, String referer, String ip, String browser, MiniURL miniURL) {
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
