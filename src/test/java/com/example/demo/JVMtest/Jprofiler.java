package com.example.demo.JVMtest;

import java.util.ArrayList;

public class Jprofiler {
    byte[] array = new byte[1024 * 1024];

    public static void main(String[] args) {
        ArrayList<Jprofiler> list = new ArrayList<>();
        int count = 0;

        try {
            while (true) {
                list.add(new Jprofiler());
                count++;
            }
        }catch (Exception outOfMemoryError) {
            System.out.println("count : " + count);
            outOfMemoryError.printStackTrace();
        }
    }
}
