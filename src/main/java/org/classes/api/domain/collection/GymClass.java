package org.classes.api.domain.collection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.classes.api.domain.user.UserDTO;
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

    @NotBlank(message="Class name field must be filled")
    @NotNull(message ="Class name is required")
    private String className;

    @NotBlank(message="Coach name must be filled")
    @NotNull(message ="Coach name is required")
    private String coachName;

    private String classTime;

    private List<UserDTO> users;

}
