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
    @Column(name = "ownershipId")
    private Integer ownershipId;
    /**
     * Id of Parking Lot
     */


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parkingLotId", referencedColumnName="parkingLotId")
    private ParkingLot parkingLot;

    /**
     * Id of supervisor (accountId with role is 2)
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="supervisorId", referencedColumnName="accountId")
    private Account supervisor;

    public Ownership() {
    }

    /**
     * Constructor full arguments
     */
    public Ownership(ParkingLot parkingLot, Account supervisor) {
        this.parkingLot = parkingLot;
        this.supervisor = supervisor;
    }

    public Integer getOwnershipId() {
        return ownershipId;
    }

    public void setOwnershipId(Integer ownershipId) {
        this.ownershipId = ownershipId;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Account getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Account supervisor) {
        this.supervisor = supervisor;
    }
}
