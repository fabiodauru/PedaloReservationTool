package com.pedaloreservation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    //@Autowired

    DBAccess dbAccess = new DBAccess();
    private List<Reservation> reservations = getAllReservations();

    public void createReservation(Reservation reservation) {
        dbAccess.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        reservations = dbAccess.getAllReservations();
        return reservations.stream()
                .sorted(Comparator.comparing(Reservation::getDate)
                        .thenComparing(Reservation::getStartTime))
                .collect(Collectors.toList());
    }

    public void deleteReservation(ObjectId objectId) {

        for (Reservation reservation: reservations) {
            if (reservation.getId().equals(objectId)) {
                dbAccess.deleteReservation(reservation);
                break;
            }
        }

    }
}