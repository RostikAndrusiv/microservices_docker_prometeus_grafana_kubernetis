package com.rostik.andrusiv.microrecipient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long messageId;

    private String uzer;

    private String message;
}
