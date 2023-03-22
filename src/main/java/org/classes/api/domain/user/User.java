package org.classes.api.domain.user;

import lombok.Data;
import org.classes.api.domain.dto.GymClassDTO;

import java.util.List;

@Data
public class User {

    private String id;

    private String idNum;

    private String userName;

    private String userEmail;

    private Boolean subscribed = true;

}
