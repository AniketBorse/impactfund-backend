package com.aniket.impactfund.investor.repository;

import com.aniket.impactfund.investor.entity.Investor;
import com.aniket.impactfund.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
    boolean existsByUser(User user);

    Optional<Investor> findByUser(User user);

    Optional<Investor> findByUuid(UUID  uuid);
}
