package net.jescort.persistence.dao;

import java.util.List;
import net.jescort.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDao extends JpaRepository<Location, Integer>
{
    public List<Location> getByFirstLevel();

    public List<Location> getChildern(Integer id);
}
