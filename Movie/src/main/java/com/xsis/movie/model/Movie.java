package com.xsis.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "Movie")
@Getter
@Setter
@ToString
public class Movie extends Auditable {

    @Id
    private Long id;
    private String title;
    private String description;
    private Double rating;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
}
