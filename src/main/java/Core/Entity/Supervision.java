package Core.Entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Supervision Entity
 *
 * Author: DangNHH - 19/02/2019
 */
@Entity
@Table(name = "Supervision")
public class Supervision {
    /**
     * Id of Supervision
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supervisionId")
    private Integer supervisionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parkingLotId", referencedColumnName="parkingLotId")
    private ParkingLot parkingLot;

    /**
     * Id of supervisor (accountId with role is 2)
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="supervisorId", referencedColumnName="accountId")
    private Account supervisor;

    public Supervision() {
    }

    /**
     * Constructor full arguments
     */
    public Supervision(ParkingLot parkingLot, Account supervisor) {
        this.parkingLot = parkingLot;
        this.supervisor = supervisor;
    }

    public Integer getSupervisionId() {
        return supervisionId;
    }

    public void setSupervisionId(Integer supervisionId) {
        this.supervisionId = supervisionId;
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
