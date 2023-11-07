package com.grig.edu.shawermacloud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final String ruName;
    private final Type type;

    public enum Type {
        WRAP("Хлебная обёртка"),
        MEAT("Мясо"),
        VEGGIES("Овощи"),
        CHEESE("Сыр"),
        SAUCE("Соус");
        private final String ru;
        Type(String ru) {
            this.ru = ru;
        }
        public String ru() {
            return ru;
        }
    }
}
