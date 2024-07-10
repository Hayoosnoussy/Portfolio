package com.esprit.examen.models;

import lombok.Data;

@Data
public class StockModel {
	private Long idStock;
	private String libelleStock;
	private Integer qte;
	private Integer qteMin;
}