package edu.narxoz.galactic.task;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.drones.Drone;

public class DeliveryTask {
    private CelestialBody origin;
    private CelestialBody destination;
    private Cargo cargo;
    private TaskState taskState;
    private Drone assignedDrone;

    public DeliveryTask(CelestialBody origin, CelestialBody destination, Cargo cargo){
        this.origin = origin;
        this.destination = destination;
        this.cargo = cargo;
        taskState = TaskState.CREATED;
        assignedDrone = null;
    }

    public CelestialBody getOrigin() {
        return origin;
    }
    public CelestialBody getDestination() {
        return destination;
    }
    public Cargo getCargo() {
        return cargo;
    }
    public TaskState getTaskState() {
        return taskState;
    }
    public Drone getAssignedDrone() {
        return assignedDrone;
    }

    public double estimateTime(){
        if(assignedDrone == null){
            throw new IllegalStateException("Drone is not assigned for the task");
        }
        if(assignedDrone.speedKmPerMin() <= 0){
            throw new IllegalStateException("Drone speed must be greater than 0");
        }
        return origin.distanceTo(destination) / assignedDrone.speedKmPerMin();
    }
    private void setState(TaskState state){
        taskState = state;
    }
    private void setAssignedDrone(Drone drone){
        assignedDrone = drone;
    }

    public void assignTo(Drone drone){
        setState(TaskState.ASSIGNED);
        setAssignedDrone(drone);
        assignedDrone.startFlight();
    }
    public void setFinish(){
        setState(TaskState.DONE);
        assignedDrone.finishFlight();
    }
}

