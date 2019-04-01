package com.example.demo.entity.security;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by z00382545 on 10/20/16.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name")
    private String name;

}
