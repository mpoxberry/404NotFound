package com.hackathon.nf.model;

import java.io.Serializable;

public class FoodAlcoholPair implements Serializable{
	private String foodType;
	private String alcohol;
	
	public FoodAlcoholPair(String food, String alcohol) {
		this.alcohol = alcohol;
		this.foodType = food;
	}
}
