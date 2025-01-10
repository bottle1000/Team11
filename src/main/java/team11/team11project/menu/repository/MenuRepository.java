package team11.team11project.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team11.team11project.common.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long>{

    Optional<Menu> findMenuById(Long id);

    List<Menu> findByStoreId(Long id);
}
