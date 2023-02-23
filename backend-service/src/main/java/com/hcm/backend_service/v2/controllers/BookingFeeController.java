package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.models.BookingFee;
import com.hcm.backend_service.v2.services.BookingFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/booking-fees")
public class BookingFeeController {
    @Autowired
    BookingFeeService bookingFeeService;
    @GetMapping("/{hospitalId}")
    public List<BookingFee> getHospitalBookingFees(
            @PathVariable("hospitalId")UUID hospitalId
    ) {
        return bookingFeeService.getHospitalBookingFees(hospitalId);
    }
    @PutMapping("/{bookingFeeId}")
    public BookingFee updateBookingFee(
            @PathVariable("bookingFeeId") UUID bookingFeeId,
            @RequestBody BookingFee bookingFee
    ) {
        return bookingFeeService.updateBookingFee(bookingFeeId, bookingFee);
    }
}
