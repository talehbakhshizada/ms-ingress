package az.company.msingress.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagEntity tagEntity)) return false;
        return Objects.equals(getId(), tagEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}