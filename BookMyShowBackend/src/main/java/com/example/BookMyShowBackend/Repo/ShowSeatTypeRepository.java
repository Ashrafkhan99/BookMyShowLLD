package com.example.BookMyShowBackend.Repo;

import com.example.BookMyShowBackend.model.Show;
import com.example.BookMyShowBackend.model.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

    List<ShowSeatType> findAllByShow(Show show);
}

