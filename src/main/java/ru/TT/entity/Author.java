package ru.T.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "введите имя")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "введите фамилию")
    private String surname;

   @ManyToMany (mappedBy = "authors")
    private Set <Book> books;

}
