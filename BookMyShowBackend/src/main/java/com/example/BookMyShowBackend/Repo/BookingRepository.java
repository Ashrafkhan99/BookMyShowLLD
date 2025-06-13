package com.example.BookMyShowBackend.Repo;

import com.example.BookMyShowBackend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Override
    Booking save(Booking entity);
}
