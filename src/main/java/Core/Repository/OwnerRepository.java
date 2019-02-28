package Core.Repository;

import Core.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Owner findByOwnerId(Integer id);
}
