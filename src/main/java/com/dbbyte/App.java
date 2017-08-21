package com.dbbyte;

import io.reactivex.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App {

    public String getGreeting() {
        return "Hello World";
    }


    public static Observable<Integer> fakeUserInput() {

        Random random =new Random();

//        return Observable.just(10,5,2,45)
//                .map(number -> random.nextInt(20))
//                .filter(line -> line%2==0); //impure function which is not ok FP
        return Observable.intervalRange(0,10,500,500, TimeUnit.MICROSECONDS)
                .concatMap(number -> Observable.just(random.nextInt(20))
                .delay(random.nextInt(500), TimeUnit.MILLISECONDS));
//                .map(number -> random.nextInt(20)); //impure function which is not ok FP
    }

    public static void main(String[] args){

//        Observable.just("Hello Another World")
        fakeUserInput()
                .blockingSubscribe(line -> System.out.println(line));
//                .subscribe(line -> System.out.println(line));

        System.out.println(new App().getGreeting());
    }

}
