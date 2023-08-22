package az.company.msingress.task;

import java.util.List;

class Transport {
}

class Car extends Transport {

}

class BMW extends Car {

}

public class PECS {
    public void upperBounding(List<? extends Car> list) {
        Car c = list.get(0);
        Transport t = list.get(1);
        // BMW b = list.get(2);
        // list.add(new Car());
        list.add(null);
    }

    public void lowerBounding(List<? super Car> list) {
        // Car c = list.get(0);
        // Transport t = list.get(1);
        //BMW b = list.get(2);
        Object o = list.get(3);
       // list.add(new Transport());
        list.add(new Car());
        list.add(new BMW());
    }
}
