package com.nnk.springboot.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer Id;

    @Column(name = "curveId", nullable = false)
    private Integer curveId;

    @Column(name = "asOfDate")
    private Timestamp asOfDate;

    @Column(name = "term", nullable = false)
    private Double term;

    @Column(name = "value", nullable = false)
    private Double value;

    @CreationTimestamp
    @Column(name = "creationDate", nullable = false, updatable = false)
    private Timestamp creationDate;

    public CurvePoint(int curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }
}
