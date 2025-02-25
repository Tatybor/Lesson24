package ru.T.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookDTO {

    private Long id;
    @NotBlank(message = "введите название книги")
    private String name;
    @Size(min = 3,max = 15)
    private String genre;
    private List<AuthorDTO> authors;

}





