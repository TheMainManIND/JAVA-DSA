package com.company;

import java.util.ArrayList;

class QuadraticPr{
    String[] hashtable;
    int usedCellNumber;

    QuadraticPr(int size){
        hashtable = new String[size];
        usedCellNumber = 0;
    }

    public int modASCIIFunction(String word, int M){
        char ch[];
        ch = word.toCharArray();
        int i,sum;
        for (sum=0,i=0;i<word.length();i++){
            sum = sum+ch[i];
        }
        return sum % M;
    }

    //Get Load Factor
    public double getLoadFactor(){
        double lf = usedCellNumber * 1.0/hashtable.length;
        return lf;
    }

    //Rehash
    public void rehashKeys(String word){
        usedCellNumber = 0;
        ArrayList<String> data = new ArrayList<String>();
        for (String s: hashtable){
            if (s!= null){
                data.add(s);
            }
            data.add(word);
            hashtable = new String[hashtable.length*2];
            for (String e:data){
                //InsertIn Hash Table
                insertKeyInHashTable(e);
            }
        }
    }

    //Insertin Hash Table
    public void insertKeyInHashTable(String word){
        double LoadFactor = getLoadFactor();
        if (LoadFactor>=0.75){
            rehashKeys(word);
        }else {
            int index = modASCIIFunction(word, hashtable.length);
            int counter = 0;
            for (int i=index;i<index+hashtable.length;i++){
                int newIndex = (index + (counter * counter))%hashtable.length;
                if (hashtable[newIndex]==null){
                    hashtable[newIndex] = word;
                    System.out.println(word+" has been Successfully Inserted at location: "+newIndex );
                    break;
                }else {
                    System.out.println("Index "+newIndex+" has been already Occupied, Trying next empty cell....");

                }
                counter++;
            }
        }
        usedCellNumber++;
    }

    //Display
    public void display(){
        if (hashtable == null){
            System.out.println("\n Hash Table does not exists");
            return;
        }else {
            System.out.println("\n-------------------Hash Table------------------------");
            for (int i=0;i<hashtable.length;i++){
                System.out.println("Index: "+i+", Key: "+hashtable[i]);

            }
        }
    }

    //Search
    public boolean search(String word){
        int index = modASCIIFunction(word, hashtable.length);
        for (int i = index;i<index+hashtable.length;i++){
            int newIndex = i % hashtable.length;
            if (hashtable[newIndex]!=null && hashtable[newIndex].equals(word)){
                System.out.println(word+" is present in the Hash Table at location: "+ newIndex);
                return true;
            }
        }
        System.out.println(word+" is not present in the hash table");
        return false;
    }

    //Delete Function
    public void deleteFun(String word){
        int index = modASCIIFunction(word, hashtable.length);
        for (int i= index;i<index+hashtable.length;i++) {
            int newIndex = i % hashtable.length;
            if (hashtable[newIndex] != null && hashtable[newIndex].equals(word)) {
                hashtable[newIndex] = null;
                System.out.println(word+" has been Successfully deleted");
                return;
            }
        }
        System.out.println(word+" is not present in the hash table");
    }
}

public class QuadraticProbing {
    public static void main(String[] args) {
        QuadraticPr q = new QuadraticPr(13);
        q.insertKeyInHashTable("The");
        q.insertKeyInHashTable("quick");
        q.insertKeyInHashTable("brown");
        q.insertKeyInHashTable("fox");
        q.insertKeyInHashTable("over");
        q.insertKeyInHashTable("lazy");
        q.display();
    }
}

