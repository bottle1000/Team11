package team11.team11project.menu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class dto {

    private String name;

    private Integer price;

    private String description;

    private LocalDate createdAt;

}
