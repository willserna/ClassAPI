package org.classes.api.domain.user;

import lombok.Data;

@Data
public class UserDTO {

    private String id;

    private String idNum;

    private String userName;

    private String userEmail;

    private Boolean subscribed = true;

}
