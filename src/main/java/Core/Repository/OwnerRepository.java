package Core.Repository;

import Core.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Owner findByOwnerId(Integer id);
    @Query("SELECT o " +
            "FROM Owner o " +
            "WHERE o.firstName LIKE CONCAT('%',:searchValue,'%') OR o.lastName LIKE CONCAT('%',:searchValue,'%')")
    List<Owner> searchOwnerByName(@Param("searchValue") String  searchValue);
}
