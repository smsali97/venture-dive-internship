
package com.crm.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Contact {
    private @GeneratedValue @Id long id;
    private String firstName;
    private String lastName;
    private String email;

    public Contact() {

    }



    public Contact(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}











