package br.com.template.infra.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class EntityBase<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected ID id;
}