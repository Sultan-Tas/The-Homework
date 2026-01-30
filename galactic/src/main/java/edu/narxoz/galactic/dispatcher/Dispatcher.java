package edu.narxoz.galactic.dispatcher;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;


public class Dispatcher {
    public Result assignTask(DeliveryTask task, Drone drone){
        System.out.println("Attempting to deliver \'" + task.getCargo().getDescription() + "\' with " + drone.getId());

        //imitation of loading process
        for(int i = 0; i < 3; i++){
            System.out.print(".");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\b");
            System.out.print("..");try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\b\b");
            System.out.print("...");try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\b\b\b");
        }

        if(task == null){
            return new Result(false, "Task is null");
        }
        if(drone == null){
            return new Result(false, "Drone is null");
        }
        if(drone.getStatus() != DroneStatus.IDLE){
            return new Result(false, "Drone is not IDLE");
        }
        if(task.getCargo().getWeight() > drone.getMaxPayloadKg()){
            return new Result(false, "Cargo exceeds max payload");
        }
        if(task.getTaskState() != TaskState.CREATED){
            return new Result(false, "Task is not CREATED");
        }
        task.assignTo(drone);
        System.out.println("Estimated time: " + task.estimateTime() + " minutes");
        return new Result(true, null);
    }


    public Result completeTask(DeliveryTask task){
        System.out.println("Trying to complete \'" + task.getCargo().getDescription() + "\' delivery...");

        //waiting time is tailored to estimated time
        if(task.getAssignedDrone() != null) {
            try {
                Thread.sleep((long) task.estimateTime() * 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if(task == null){
            return new Result(false, "Task is null");
        }
        if(task.getTaskState() != TaskState.ASSIGNED){
            return new Result(false, "Task is not assigned");
        }
        if(task.getAssignedDrone() == null){
            return new Result(false, "Drone is null");
        }
        if(task.getAssignedDrone().getStatus() != DroneStatus.IN_FLIGHT){
            return new Result(false, "Drone is not in flight");
        }
        task.setFinish();
        System.out.println(" Drone status: " + task.getAssignedDrone().getStatus() +
                "\n Task status: " + task.getTaskState());
        return new Result(true, null);
    }
}
