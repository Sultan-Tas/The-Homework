package edu.narxoz.galactic.drones;

public abstract class Drone {
    private String id;
    private DroneStatus status;
    private double maxPayloadKg;

    protected Drone(String id, double maxPayloadKg) {
        this.id = id;
        if(maxPayloadKg <= 0){
            throw new IllegalArgumentException("MaxPayload must be greater than 0");
        }
        this.maxPayloadKg = maxPayloadKg;
        status = DroneStatus.IDLE;
    }

    public String getId(){
        return id;
    }
    public DroneStatus getStatus(){
        return status;
    }
    public double getMaxPayloadKg(){
        return maxPayloadKg;
    }

    public abstract double speedKmPerMin();

    protected void setStatus(DroneStatus status){
        this.status = status;
    }
    public void startFlight(){
        setStatus(DroneStatus.IN_FLIGHT);
    }
    public void finishFlight(){
        setStatus(DroneStatus.IDLE);
    }
}
