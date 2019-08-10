package com.kamal.profiles.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.kamal.profiles.domain.Profile;

@Repository
public interface ProfilesRepository extends CassandraRepository<Profile, Long> {
	//
}
