package com.ssafit.model.dto;

public class RoutineComponents {
	private int routineComponentsId;
	private int exerciseId;
	private int routineId;
	private int routineComponetsReps;
	private int routineComponetsWeight;

	public RoutineComponents() {
	}

	public RoutineComponents(int routineComponentsId, int exerciseId, int routineId, int routineComponetsReps,
			int routineComponetsWeight) {
		this.routineComponentsId = routineComponentsId;
		this.exerciseId = exerciseId;
		this.routineId = routineId;
		this.routineComponetsReps = routineComponetsReps;
		this.routineComponetsWeight = routineComponetsWeight;
	}

	public int getRoutineComponentsId() {
		return routineComponentsId;
	}

	public void setRoutineComponentsId(int routineComponentsId) {
		this.routineComponentsId = routineComponentsId;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public int getRoutineId() {
		return routineId;
	}

	public void setRoutineId(int routineId) {
		this.routineId = routineId;
	}

	public int getRoutineComponetsReps() {
		return routineComponetsReps;
	}

	public void setRoutineComponetsReps(int routineComponetsReps) {
		this.routineComponetsReps = routineComponetsReps;
	}

	public int getRoutineComponetsWeight() {
		return routineComponetsWeight;
	}

	public void setRoutineComponetsWeight(int routineComponetsWeight) {
		this.routineComponetsWeight = routineComponetsWeight;
	}

	@Override
	public String toString() {
		return "RoutineComponents [routineComponentsId=" + routineComponentsId + ", exerciseId=" + exerciseId
				+ ", routineId=" + routineId + ", routineComponetsReps=" + routineComponetsReps
				+ ", routineComponetsWeight=" + routineComponetsWeight + "]";
	}

}
