package com.pedaloreservation;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

//@Document(collection = "reservations")
public class Reservation {
    private ObjectId id;
    private LocalDate date;
    private LocalTime startTime;
    private int duration;
    private int pedalSize;

    // Getter und Setter

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPedalSize() {
        return pedalSize;
    }

    public void setPedalSize(int pedalSize) {
        this.pedalSize = pedalSize;
    }

    public ObjectId getId() { return id; }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Reservation() {
    }

    public Reservation(LocalDate date, LocalTime startTime, int duration, int pedalSize) {
        this.id = new ObjectId();
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.pedalSize = pedalSize;
    }


    public int compareTo(Reservation reservation){
        int dateCompare = this.date.compareTo(reservation.date);
        if (dateCompare == 0){
            return this.startTime.compareTo(reservation.startTime);

        }
        return dateCompare;
    }
}
