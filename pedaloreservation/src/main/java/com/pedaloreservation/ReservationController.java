package com.pedaloreservation;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReservationController {


    private ReservationService reservationService = new ReservationService();

    /*@GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }
    */

    @GetMapping
    @RequestMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservationForm";
    }



    @PostMapping("/createReservation")
    public String createReservation(@ModelAttribute Reservation reservation, Model model) {
        model.addAttribute("reservation", reservation);
        reservationService.createReservation(reservation);
        model.addAttribute("reservations", reservationService);
        return "redirect:/form";
    }


    /*
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable String id) {
        return reservationService.getReservationById(id)
                .map(reservation -> new ResponseEntity<>(reservation, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable String id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation)
                .map(updatedReservation -> new ResponseEntity<>(updatedReservation, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    */
    /*
    @DeleteMapping("/delete")
    public void deleteReservation(@RequestParam("index") int index, Model model) {
        reservationService.deleteReservation(index);
    }*/

    @PostMapping("/deleteReservation")
    public String deleteReservation(@RequestParam("objectId") ObjectId objectId) {
        reservationService.deleteReservation(objectId);
        return "redirect:/form"; // Ersetze "/currentPage" durch die URL der aktuellen Seite
    }

}
