package fpt.intern.fa_api.model.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class UserClassId implements Serializable {
    private Long userId;
    private Long classId;
}
