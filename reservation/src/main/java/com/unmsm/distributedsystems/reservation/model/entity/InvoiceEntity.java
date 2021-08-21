package com.unmsm.distributedsystems.reservation.model.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "client_id")
    Integer clientId;

    @Column(name = "total_igv")
    Double igvTotal;

    @Column(name = "total_invoice")
    Double invoiceTotal;
}
