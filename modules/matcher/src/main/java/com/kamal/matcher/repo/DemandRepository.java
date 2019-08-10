package com.kamal.matcher.repo;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.kamal.matcher.domain.Demand;

@Repository
public interface DemandRepository extends CassandraRepository<Demand, Long> {
	//
}