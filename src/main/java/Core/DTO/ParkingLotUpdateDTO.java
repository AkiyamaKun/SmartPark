package Core.DTO;

public class ParkingLotUpdateDTO {
    private Integer parkingLotId;
    private String displayName;
    private float longitude;
    private float latitude;
    private String address;
    private String phoneNumber;
    private String timeOfOperation;
    private Integer ownerId;
    private boolean isActive;
    private Integer totalSlot;
    private byte[] parklotImage;
    private float price;

    public ParkingLotUpdateDTO() {
    }

    public ParkingLotUpdateDTO(Integer parkingLotId, String displayName, float longitude, float latitude,
                               String address, String phoneNumber, String timeOfOperation, Integer ownerId,
                               boolean isActive, Integer totalSlot, byte[] parklotImage, float price) {
        this.parkingLotId = parkingLotId;
        this.displayName = displayName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.timeOfOperation = timeOfOperation;
        this.ownerId = ownerId;
        this.isActive = isActive;
        this.totalSlot = totalSlot;
        this.parklotImage = parklotImage;
        this.price = price;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTimeOfOperation() {
        return timeOfOperation;
    }

    public void setTimeOfOperation(String timeOfOperation) {
        this.timeOfOperation = timeOfOperation;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getTotalSlot() {
        return totalSlot;
    }

    public void setTotalSlot(Integer totalSlot) {
        this.totalSlot = totalSlot;
    }

    public byte[] getParklotImage() {
        return parklotImage;
    }

    public void setParklotImage(byte[] parklotImage) {
        this.parklotImage = parklotImage;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
