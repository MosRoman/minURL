package com.gmail.morovo1988.minURL.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "MiniURL")
public class MiniURL {
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @SequenceGenerator(name = "native", initialValue = 5, allocationSize = 1)
    @Id
    @Column(name = "Id", nullable = false)
    private Long id;
    @NotNull
    private String longAdress;

    private String smallAdress = "http://loca.ly/" + getAlphaNumericString();

    private LocalDate dayCreate = LocalDate.now();

    private LocalDate dayDestroy = LocalDate.now().plusDays(15);

    private boolean active = true;

    @OneToMany(mappedBy = "miniURL")
    private List<Statistic> statistics;

    public MiniURL() {
    }

    public MiniURL(@NotNull String longAdress, LocalDate dayDestroy) {
        this.longAdress = longAdress;
        this.dayDestroy = dayDestroy;
    }

    public MiniURL(String longAdress) {
        this.longAdress = longAdress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongAdress() {
        return longAdress;
    }

    public void setLongAdress(String longAdress) {
        this.longAdress = longAdress;
    }

    public String getSmallAdress() {
        return smallAdress;
    }

    public void setSmallAdress(String smallAdress) {
        this.smallAdress = smallAdress;
    }

    public LocalDate getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(LocalDate dayCreate) {
        this.dayCreate = dayCreate;
    }

    public LocalDate getDayDestroy() {
        return dayDestroy;
    }

    public void setDayDestroy(LocalDate dayDestroy) {
        this.dayDestroy = dayDestroy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Statistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistic> statistics) {
        this.statistics = statistics;
    }


    private String getAlphaNumericString() {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "MiniURL{" +
                "id=" + id +
                ", longAdress='" + longAdress + '\'' +
                ", smallAdress='" + smallAdress + '\'' +
                ", dayCreate=" + dayCreate +
                ", dayDestroy=" + dayDestroy +
                ", active=" + active +
                ", statistics=" + statistics +
                '}';
    }
}
