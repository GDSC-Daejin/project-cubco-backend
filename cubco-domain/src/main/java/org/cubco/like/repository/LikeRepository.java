package org.cubco.like.repository;

import org.cubco.curation.domain.Curation;
import org.cubco.like.domain.Like;
import org.cubco.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    int countByCuration(Curation curation);

    @Query("SELECT l.curation, COUNT(l) FROM Like l WHERE l.curation IN :curations GROUP BY l.curation")
    List<Object[]> countByCurations(List<Curation> curations);

    boolean existsLikeByUserAndCuration(User user, Curation curation);
    Optional<Like> findLikeByUserAndCuration(User user, Curation curation);
    void deleteByCuration(Curation curation);
}
