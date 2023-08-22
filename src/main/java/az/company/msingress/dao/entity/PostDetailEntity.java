package az.company.msingress.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_details")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    LocalDateTime createdOn;

    @Column(name = "created_by")
    String createdBy;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @ToString.Exclude
    PostEntity post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostDetailEntity that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
