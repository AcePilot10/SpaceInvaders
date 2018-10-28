package com.codygordon.spaceinvaders.builders;

public class GameBuilder {

	public static BarrierBuilder getBarrierBuilder() {
		return new BarrierBuilder();
	}
	
	public static PlayerBuilder getPlayerBuilder() {
		return new PlayerBuilder();
	}
	
	public static EnemyWaveBuilder getEnemyWaveBuilder() {
		return new EnemyWaveBuilder();
	}
}