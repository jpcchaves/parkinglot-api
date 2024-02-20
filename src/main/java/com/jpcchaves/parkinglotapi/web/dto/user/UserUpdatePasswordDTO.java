package com.jpcchaves.parkinglotapi.web.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserUpdatePasswordDTO {
    @NotBlank(message = "A senha atual e obrigatoria")
    @Size(min = 6, message = "O campo precisa ter pelo menos 6 caracteres")
    private String currentPassword;
    @NotBlank(message = "A nova senha e obrigatoria")
    @Size(min = 6, message = "O campo precisa ter pelo menos 6 caracteres")
    private String newPassword;
    @NotBlank(message = "A confirmacao da nova senha e obrigatoria")
    @Size(min = 6, message = "O campo precisa ter pelo menos 6 caracteres")
    private String confirmNewPassword;

    public UserUpdatePasswordDTO() {
    }

    public UserUpdatePasswordDTO(String currentPassword,
                                 String newPassword,
                                 String confirmNewPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
