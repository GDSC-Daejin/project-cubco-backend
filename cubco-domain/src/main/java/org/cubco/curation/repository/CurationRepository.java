package org.cubco.curation.repository;

import org.cubco.curation.domain.Curation;
import org.cubco.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurationRepository extends JpaRepository<Curation, Long> {
    boolean existsByUserAndId(User user, Long curationId);
}