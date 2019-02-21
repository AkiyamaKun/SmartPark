package Core.Entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Ownership Entity
 *
 * Author: DangNHH - 19/02/2019
 */
@Entity
@Table(name = "Ownership")
public class Ownership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ownershipId;
    /**
     * Id of Parking Lot
     */

    private Integer parkingLotId;
    /**
     * Id of supervisor (accountId with role is 2)
     */

    private Integer supervisorId;

    public Ownership() {
    }

    /**
     * Constructor full arguments
     */
    public Ownership(Integer parkingLotId, Integer supervisorId) {
        this.parkingLotId = parkingLotId;
        this.supervisorId = supervisorId;
    }

    public Integer getOwnershipId() {
        return ownershipId;
    }

    public void setOwnershipId(Integer ownershipId) {
        this.ownershipId = ownershipId;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }
}
