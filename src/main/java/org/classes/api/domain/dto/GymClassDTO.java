package org.classes.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.classes.api.domain.user.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymClassDTO {

    private String id;

    private String className;

    private String coachName;

    private String classTime;

    private List<User> users;
}
