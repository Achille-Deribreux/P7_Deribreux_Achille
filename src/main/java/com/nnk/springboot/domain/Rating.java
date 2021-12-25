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
@Table(name = "rating")
public class Rating {
    @Column(name = "moodysRating", nullable = false)
    private String moodysRating;

    @Column(name = "SandPRating", nullable = false)
    private String sandPRating;

    @Column(name = "fitchRating", nullable = false)
    private String fitchRating;

    @Column(name = "orderNumber", nullable = false)
    private Integer orderNumber;
}
