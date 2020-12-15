package com.example.Study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymnetType {

    CASH(0, "결제방법", "현금결제"),
    CARD(1, "결제방법", "카드결제")
    ;

    private Integer id;
    private String title;
    private String description;
}
