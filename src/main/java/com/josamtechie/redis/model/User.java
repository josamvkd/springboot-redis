package com.josamtechie.redis.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="USER_TBL")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name ="USER_ID")
    private long id;
    @Column(name ="USER_NAME")
    private String userName;
    @Column(name ="EMAIL_ID")
    private String emailId;
    @Column(name ="PHONE")
    private String phoneNo;
}
