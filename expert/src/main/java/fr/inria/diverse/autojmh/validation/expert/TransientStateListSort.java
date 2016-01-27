package fr.inria.diverse.autojmh.validation.expert;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * ---- Microbenchmark 4 -----
 *
 * Microbenchmark to measure the time it takes to measure a call to .sort()
 * in an ArrayList with 10 elements
 *
 * Created by marodrig on 07/12/2015.
 */

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class TransientStateListSort {

    static final Comparator<Integer> comparator = new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            /*
            if (o1 < o2) return -1;
            else if (o1 > o2) return 1;*/
            return o1 - o2;
        }
    };



    @Benchmark
    public ArrayList<Integer> baselineValidated() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(7);
        a.add(1);
        a.add(9);
        a.add(6);
        a.add(0);
        a.add(3);
        a.add(2);
        a.add(4);
        a.add(5);
        a.add(8);
        return a;
    }

    @Benchmark
    public ArrayList<Integer> benchSortArrayListValidated() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(7);
        a.add(1);
        a.add(9);
        a.add(6);
        a.add(0);
        a.add(3);
        a.add(2);
        a.add(4);
        a.add(5);
        a.add(8);
        a.sort(comparator);
        return a;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TransientStateListSort.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}

