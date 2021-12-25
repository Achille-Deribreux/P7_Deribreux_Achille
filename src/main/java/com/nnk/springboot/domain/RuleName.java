package com.nnk.springboot.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "rulename")
public class RuleName {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "json", nullable = false)
    private String json;

    @Column(name = "template", nullable = false)
    private String template;

    @Column(name = "sqlStr", nullable = false)
    private String sqlStr;

    @Column(name = "sqlPart", nullable = false)
    private String sqlPart;
}
