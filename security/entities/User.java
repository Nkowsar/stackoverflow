package stackOverFlow.security.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String fullName;
    private String email;
    private String password;
}
