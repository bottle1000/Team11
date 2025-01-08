package team11.team11project.menu.model.response;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class menuResponse {

    private Long id;

    private String name;

    private int price;

    private String description;

    private LocalDate createdAt;

    public menuResponse(Long id, String name, Integer price, String description, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
    }

}
