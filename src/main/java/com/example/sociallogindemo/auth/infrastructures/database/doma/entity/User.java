package com.example.sociallogindemo.auth.infrastructures.database.doma.entity;

import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;

@Data
@Entity
public class User {

    @Id
    Integer id;

    String name;

    String password;

}
