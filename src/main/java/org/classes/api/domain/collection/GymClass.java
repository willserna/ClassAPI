package org.classes.api.domain.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.classes.api.domain.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "classes")
public class GymClass {

    @Id
    private String id = UUID.randomUUID().toString();

    private String className;

    private String coachName;

    private String classTime;

    private List<User> users;

}
