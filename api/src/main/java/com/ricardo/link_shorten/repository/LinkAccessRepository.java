package com.ricardo.link_shorten.repository;

import com.ricardo.link_shorten.model.entity.LinkAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LinkAccessRepository extends JpaRepository<LinkAccess, UUID> {}
