package com.unmsm.distributedsystems.reservation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ClientDto implements Serializable {

  String ruc;
  String name;
}
