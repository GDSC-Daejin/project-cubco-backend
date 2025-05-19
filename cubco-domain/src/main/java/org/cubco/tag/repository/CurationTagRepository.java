package org.cubco.tag.repository;

import org.cubco.curation.domain.Curation;
import org.cubco.tag.domain.CurationTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurationTagRepository extends JpaRepository<CurationTag, Long> {
    List<CurationTag> findAllByCuration(Curation curation);
}
