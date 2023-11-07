package com.grig.edu.shawermacloud.models;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ShawermaOrder {

    private Long id;
    private Date placedAt = new Date();

    @NotBlank(message = "Обязательно для заполнения")
    private String deliveryName;
    @NotBlank(message = "Обязательно для заполнения")
    private String deliveryCity;
    @NotBlank(message = "Обязательно для заполнения")
    private String deliveryStreet;
    @NotBlank(message = "Обязательно для заполнения")
    private String deliveryState;

    @NotBlank(message = "Обязательно для заполнения")
    @Digits(integer = 6, fraction = 0, message = "Почтовый индекс должен содержать 6 цифр")
    private String deliveryZip;

    //@CreditCardNumber
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)(20[2-9][2-9])$")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Некорректный cvv")
    private String ccCVV;

    private List<Shawerma> shawermaList = new ArrayList<>();

    public void addShawerma(Shawerma shawa) {
        shawermaList.add(shawa);
    }
}
