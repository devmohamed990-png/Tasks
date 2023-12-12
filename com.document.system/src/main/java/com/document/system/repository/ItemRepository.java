package com.document.system.repository;

import com.document.system.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName(String name);

    @Query(value = "SELECT it.id, permission_group_id, name, type, p.user_email FROM item it\n" +
            "JOIN permission_groups pg\n" +
            "ON it.permission_group_id = pg.id\n" +
            "JOIN permissions p\n" +
            "ON pg.id = p.group_id\n" +
            "WHERE it.id=:id AND p.user_email=:email", nativeQuery = true)
    Item getItem(@Param("id") long name,@Param("email") String email);
}
