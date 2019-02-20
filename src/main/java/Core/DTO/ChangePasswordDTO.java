package Core.DTO;

/**
 * Change Password DTO
 *
 * Author: DangNHH - 17/02/2019
 */
public class ChangePasswordDTO {
    private Integer accountId;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(Integer accountId, String oldPassword, String newPassword) {
        this.accountId = accountId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
