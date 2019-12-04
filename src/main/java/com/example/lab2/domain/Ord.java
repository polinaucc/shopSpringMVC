package com.example.lab2.domain;

import java.util.Date;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="ord")
public class Ord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToOne
    @JoinColumn(name = "id_good")
    public Good id_good;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    public Customer id_customer;
    public Date date;
}
