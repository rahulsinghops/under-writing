package com.ipru.mca.underwriting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipru.mca.underwriting.entity.UnderWriting;

public interface UnderWritingRepo extends JpaRepository<UnderWriting, Long> {

}
