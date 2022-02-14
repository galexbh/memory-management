
package com.unah.memorymanagement.utils;

import java.util.ArrayList;
import java.util.HashMap;


public class colleaguesUtils {

    public static class Buddy {
        String assignedResult, designatedResult;

        static class Pair {

            int lb, ub;

            Pair(int a, int b) {
                lb = a;
                ub = b;
            }
        }

        int size;
        ArrayList<Pair>[] arr;

        HashMap<Integer, Integer> hm;

        @SuppressWarnings("unchecked")
        public Buddy(int s) {

            size = s;
            hm = new HashMap<>();

            int x = (int) Math.ceil(Math.log(s) / Math.log(2));
            arr = new ArrayList[x + 1];

            for (int i = 0; i <= x; i++) {
                arr[i] = new ArrayList<>();
            }
            arr[x].add(new Pair(0, size - 1));
        }

        public void allocate(int s) {
            int x = (int) Math.ceil(Math.log(s) / Math.log(2));
            int i;
            Pair temp;

            if (arr[x].size() > 0) {
                temp = arr[x].remove(0);

                System.out.println("Memory since " + temp.lb
                        + " to " + temp.ub + " Assigned");


                assignedResult = "Memory since " + temp.lb + " to " + temp.ub + " Assigned";

                hm.put(temp.lb, temp.ub - temp.lb + 1);
                return;
            }
            for (i = x + 1; i < arr.length; i++) {
                if (arr[i].isEmpty()) {
                    continue;
                }
                break;
            }

            if (i == arr.length) {
                System.out.println("Error when allocating memory");

                assignedResult = "Error when allocating memory";

                return;
            }

            temp = arr[i].remove(0);

            i--;

            for (; i >= x; i--) {
                Pair newPair = new Pair(temp.lb, temp.lb + (temp.ub - temp.lb) / 2);
                Pair newPair2 = new Pair(temp.lb + (temp.ub - temp.lb + 1) / 2, temp.ub);

                arr[i].add(newPair);
                arr[i].add(newPair2);

                temp = arr[i].remove(0);
            }
            System.out.println("Memory since " + temp.lb
                    + " Memory since " + temp.ub);

            assignedResult = " " + temp.lb + " to " + temp.ub + " Assigned";

            hm.put(temp.lb, temp.ub - temp.lb + 1);
        }

        public void deallocate(int s) {
            if (!hm.containsKey(s)) {
                System.out.println("Error when designating memory");

                designatedResult = "Error when designating memory";

                return;
            }

            int x = (int) Math.ceil(Math.log(hm.get(s)) / Math.log(2));
            int i, buddyNumber, buddyAddress;

            arr[x].add(new Pair(s, s + (int) Math.pow(2, x) - 1));

            System.out.println("Memory since " + s
                    + " to " + (s + (int) Math.pow(2, x) - 1) + " released");

            designatedResult = "Memory since " + s + " to " + (s + (int) Math.pow(2, x) - 1) + " released";

            buddyNumber = s / hm.get(s);

            if (buddyNumber % 2 != 0) {
                buddyAddress = s - (int) Math.pow(2, x);
            } else {
                buddyAddress = s + (int) Math.pow(2, x);
            }

            for (i = 0; i < arr[x].size(); i++) {
                if (arr[x].get(i).lb == buddyAddress) {
                    if (buddyNumber % 2 == 0) {
                        arr[x + 1].add(new Pair(s, s + 2 * ((int) Math.pow(2, x)) - 1));
                        System.out.println("Block release " + s + " y " + buddyAddress + " realized");

                        designatedResult = "Block release " + s + " y " + buddyAddress + " realized";

                    } else {
                        arr[x + 1].add(new Pair(buddyAddress, buddyAddress + 2 * ((int) Math.pow(2, x)) - 1));
                        System.out.println("Block release " + buddyAddress + " y " + s + " realized");

                        designatedResult = "Block release " + buddyAddress + " y " + s + " realized";
                    }
                    arr[x].remove(i);
                    arr[x].remove(arr[x].size() - 1);
                    break;
                }
            }
            hm.remove(s);
        }

        public String getAssignedResult() {
            return assignedResult;
        }

        public String getDesignatedResult() {
            return designatedResult;
        }


    }


}
