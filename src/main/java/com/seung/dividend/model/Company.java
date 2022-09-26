package com.seung.dividend.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String ticker;

    private String name;

}
