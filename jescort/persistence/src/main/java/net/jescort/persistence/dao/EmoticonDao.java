package net.jescort.persistence.dao;

import net.jescort.domain.forum.Emoticon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EmoticonDao extends JpaRepository<Emoticon, Integer>
{
    public Emoticon findByEmoticon(@Param("emoticon")String emoticon);
}
