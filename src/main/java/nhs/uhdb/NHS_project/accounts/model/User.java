package nhs.uhdb.NHS_project.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long user_id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String address1;
    private String address2;
    private String city;
    private String postcode;

    public User() {
        this.user_id = null;
        this.email = "";
        this.firstname = "";
        this.lastname = "";
        this.password = "";
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.postcode = "";
    }
}
