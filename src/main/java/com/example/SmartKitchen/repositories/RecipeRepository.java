package com.example.SmartKitchen.repositories;

import com.example.SmartKitchen.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByUserIdOrHiddenForOthersFalse(Long userId);
    @Query(value = "select distinct r.recipe_id, r.cook_steps, r.description, r.hidden_for_others, r.image_path, r.name, r.user_id from recipes r\n" +
            "join recipe_tags rt on rt.recipe_id = r.recipe_id\n" +
            "join tags t on rt.tag_id = t.tag_id\n" +
            "where t.name in :tags and (r.user_id = :userId or r.hidden_for_others = false)", nativeQuery = true)
    List<Recipe> findByTags(@Param("tags") List<String> tags, @Param("userId") Long userId);
}
