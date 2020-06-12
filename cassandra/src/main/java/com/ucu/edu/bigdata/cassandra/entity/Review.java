package com.ucu.edu.bigdata.cassandra.entity;

import com.ucu.edu.bigdata.cassandra.utils.BooleanToStringConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "review")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {

    @Id
    String id;

    @Column(name = "star_rating")
    Integer rating;

    @Column(name = "helpful_votes")
    Integer helpfulVotes;

    @Column(name = "total_votes")
    Integer totalVotes;

    @Convert(converter = BooleanToStringConverter.class)
    Boolean vine;

    @Column(name = "verified_purchase")
    @Convert(converter = BooleanToStringConverter.class)
    Boolean isVerified;

    String headline;

    LocalDate date;

    @Column(columnDefinition = "LONGTEXT")
    String body;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

}
