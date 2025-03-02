package ru.TT.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.TT.services.AuthorServiceImpl;

@Controller
@AllArgsConstructor
public class AuthorController {
    private final AuthorServiceImpl authorServiceImpl;

    @GetMapping("/authors")
    String getAuthorsView(Model model) {
        model.addAttribute("authors", authorServiceImpl.getAllAuthors());
        return "authors";
    }
}

/*@GetMapping("/authors")
        public List<AuthorDTO> getAllAuthors(@RequestParam(required=false) Long id,
                                             @RequestParam (required=false) String name,
                                             @RequestParam (required=false) String surname,
                                             Model model) {
            List <AuthorDTO> authors = authorServiceImpl.getAllAuthors();
            model.addAttribute("authors", authors);
            return authors;
        }
    }*/