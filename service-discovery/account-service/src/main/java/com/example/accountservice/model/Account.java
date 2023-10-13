package com.example.accountservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String id;
    private String name;
}
