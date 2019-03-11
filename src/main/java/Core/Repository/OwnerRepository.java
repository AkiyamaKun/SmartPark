package Core.Repository;

import Core.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Owner findByOwnerId(Integer id);

    @Query("select o from Owner o where o.firstName like ?1 or o.lastName like ?1")
    List<Owner> searchOwnerByName(String searchValue);
}
