package com.unmsm.distributedsystems.reservation.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class OrderRequest implements Serializable {

    Article article;
    Client client;
}
