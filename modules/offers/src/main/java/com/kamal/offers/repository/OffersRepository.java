package com.kamal.offers.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.kamal.offers.domain.Offers;

@Repository
public interface OffersRepository extends CassandraRepository<Offers, Long> {

}
