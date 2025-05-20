package org.cubco.like.repository;

import org.cubco.curation.domain.Curation;
import org.cubco.like.domain.Like;
import org.cubco.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    int countByCuration(Curation curation);
    boolean existsLikeByUserAndCuration(User user, Curation curation);
    Optional<Like> findLikeByUserAndCuration(User user, Curation curation);
}
