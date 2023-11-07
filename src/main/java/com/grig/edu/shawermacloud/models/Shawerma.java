package com.grig.edu.shawermacloud.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shawerma {

    private Long id;

    @NotNull
    @Size(min = 5, message = "Придумайте название для шаурмы, не меньше 5 букв")
    private String name;

    @NotNull
    @Size(min = 1, message = "Вы должнаы выбрать хотя бы 1 ингредиент")
    private List<Ingredient> ingredients;

    private Date createdAt = new Date();
}
