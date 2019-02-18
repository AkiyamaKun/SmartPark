package Core.DTO;

public class ParkingSlotDTO {
    private Integer slotId;
    private String name;
    private String status;
    private Integer parkingLotId;

    public ParkingSlotDTO() {
    }

    /**
     * Constructor full arguments
     * @param slotId
     * @param name
     * @param status
     * @param parkingLotId
     */
    public ParkingSlotDTO(Integer slotId, String name, String status, Integer parkingLotId) {
        this.slotId = slotId;
        this.name = name;
        this.status = status;
        this.parkingLotId = parkingLotId;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
