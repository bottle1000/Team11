package team11.team11project.menu.model.response;

import lombok.Getter;
import team11.team11project.common.entity.Menu;

import java.time.LocalDate;

@Getter
public class MenuResponse {

    private Long id;

    private String name;

    private int price;

    private String description;

    private LocalDate createdAt;

    public MenuResponse(Long id, String name, Integer price, String description, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
    }

}
