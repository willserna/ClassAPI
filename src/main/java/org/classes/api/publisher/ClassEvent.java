package org.classes.api.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.classes.api.domain.dto.GymClassDTO;

@Data
@AllArgsConstructor
public class ClassEvent {

    private String idUser;
    private GymClassDTO classSubscribed;
    private String eventType;
}
