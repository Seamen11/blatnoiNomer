package com.example.blatnoinomer.domain;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User
{
    private static final  String SEQ_NAME = "user_seq";

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator (name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String name;
    private String password;
    private String email;
    private boolean archive;
    @Enumerated(EnumType. STRING) private Role role;
    @OneToOne(cascade = CascadeType. REMOVE)
    private Bucket bucket;
}
