package fpt.intern.fa_api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;

@Entity
@Table(name = "group_user_class")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserClass {
    @EmbeddedId
    private UserClassId userClassId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("classId")
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;
}
