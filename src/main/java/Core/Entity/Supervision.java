package Core.Entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Supervision Entity
 *
 * Author: DangNHH - 19/02/2019
 */
@Entity
@Table(name = "supervision")
public class Supervision {
    /**
     * Id of Supervision
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supervision_id")
    private Integer supervisionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parking_lot_id", referencedColumnName="parking_lot_id")
    private ParkingLot parkingLot;

    /**
     * Id of supervisor (accountId with role is 2)
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="supervisor_id", referencedColumnName="account_id")
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
