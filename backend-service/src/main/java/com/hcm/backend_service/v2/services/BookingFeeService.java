package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.models.BookingFee;
import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.repositories.BookingFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingFeeService {
    @Autowired
    BookingFeeRepository bookingFeeRepository;
    @Autowired
    HospitalService hospitalService;
    public BookingFee createBookingFee(BookingFee bookingFee) {
        return bookingFeeRepository.save(bookingFee);
    }

    public List<BookingFee> getHospitalBookingFees(UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        return bookingFeeRepository.findByHospital(hospital);
    }
    public BookingFee getBookingFeeById(UUID bookingFeeId) {
        Optional<BookingFee> bookingFeeOptional =
                bookingFeeRepository.findById(bookingFeeId);
        if(!bookingFeeOptional.isPresent()) {
            throw new BadRequestException("booking fee not found");
        }
        return bookingFeeOptional.get();
    }

    public BookingFee updateBookingFee(UUID bookingFeeId, BookingFee bookingFee) {
        BookingFee fee =
                getBookingFeeById(bookingFeeId);
        fee.setFee(bookingFee.getFee());
        fee.setCurrency(bookingFee.getCurrency());
        return bookingFeeRepository.save(fee);
    }
}
