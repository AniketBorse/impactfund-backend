package com.aniket.impactfund.borrower.repository;

import com.aniket.impactfund.borrower.entity.Borrower;
import com.aniket.impactfund.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    boolean existsByUser(User user);
    
    Optional<Borrower> findByUser(User user);
    
    Optional<Borrower> findByUuid(UUID uuid);

    boolean existsByPanNumber(String panNumber);

    boolean existsByAadhaarNumber(String aadhaarNumber);
}
