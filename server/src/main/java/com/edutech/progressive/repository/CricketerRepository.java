package com.edutech.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Cricketer;

@Repository
public interface CricketerRepository extends JpaRepository<Cricketer,Integer> {
}
