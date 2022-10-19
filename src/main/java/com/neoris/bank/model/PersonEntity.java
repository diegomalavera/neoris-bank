package com.neoris.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId", nullable = false)
    private Long personId;
    private String name;
    private String gender;
    private Long age;
    private Long idNumber;
    private String address;
    private String phone;
}
