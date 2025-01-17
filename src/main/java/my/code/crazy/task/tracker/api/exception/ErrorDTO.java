package my.code.crazy.task.tracker.api.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorDTO {
    String error;


    String errorDescription;

}
