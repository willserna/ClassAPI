package org.classes.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.classes.api.domain.user.UserDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymClassDTO {

    private String id;

    @NotBlank(message="Class name field must be filled")
    @NotNull(message ="Class name is required")
    private String className;

    @NotBlank(message="Coach name must be filled")
    @NotNull(message ="Coach name is required")
    private String coachName;

    private String classTime;

    private List<UserDTO> users;
}
