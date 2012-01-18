package net.jescort.persistence.dao;

import java.util.List;

import net.jescort.domain.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address, Integer>
{
    public List<Address> findByUserId(int userId);

    public List<Address> findByUserId(int userId, int offset, int limit);
}
