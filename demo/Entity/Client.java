package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("client")
public class Client extends User{
    @Column(name = "loyalty_card")
    private boolean LoyaltyCard;
}
