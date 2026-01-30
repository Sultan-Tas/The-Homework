package edu.narxoz.galactic;

import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.bodies.SpaceStation;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.drones.HeavyDrone;
import edu.narxoz.galactic.drones.LightDrone;
import edu.narxoz.galactic.task.DeliveryTask;

public class Demo_prg {
    public static void main(String[] args) {
        Planet Earth = new Planet("Earth", 0, 0, "safe for breathing");
        Planet Mars = new Planet("Earth", 0, 0, "not safe");

        SpaceStation ISS = new SpaceStation("ISS", 0, 400, 400);
        LightDrone light_1 = new LightDrone("light_1", 200);
        HeavyDrone heavy_1 = new HeavyDrone("heavy_1", 1000);

        Cargo nutrients = new Cargo(200, "Food and perishables");
        Cargo metalParts = new Cargo(400, "Metal and electronics");

        DeliveryTask task_1 = new DeliveryTask(Earth, Mars, nutrients);
        DeliveryTask task_2 = new DeliveryTask(Earth, ISS, metalParts);

        Dispatcher dispatcher = new Dispatcher();
        System.out.println(dispatcher.assignTask(task_2, light_1));
        System.out.println(dispatcher.completeTask(task_2));
        System.out.println();
        System.out.println(dispatcher.assignTask(task_2, heavy_1));
        System.out.println(dispatcher.completeTask(task_2));
    }
}
