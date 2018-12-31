package tech.overpass.conferwebforum.model.dto;

public class UserDto {

    private String username;
    private String fullName;
    private String password;
    private String confirmPassword;
    private String email;

    public UserDto() {
    }

    public UserDto(String username, String fullName, String password, String confirmPassword, String email) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRegistrationDto toUserRegistrationDto() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUsername(username);
        userRegistrationDto.setFullName(fullName);
        userRegistrationDto.setEmail(email);
        userRegistrationDto.setConfirmEmail(email);
        userRegistrationDto.setPassword(password);
        userRegistrationDto.setConfirmPassword(confirmPassword);
        userRegistrationDto.setTerms(true);
        return userRegistrationDto;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
