package com.company;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println(numToString(3568923425L));
    }


    static String[] untillTwenty = {
            " ",
            "մեկ",
            "երկու",
            "երեք",
            "չորս",
            "հինգ",
            "վեց",
            "յոթ",
            "ութ",
            "ինը",
            "տաս",
            "տասնմեկ",
            "տասներկու",
            "տասներեք",
            "տասնչորս",
            "տասնհինգ",
            "տասնվեց",
            "տասնյոթ",
            "տասնութ",
            "տասնինը",
    };

    static String[] untilHundred = {
            " ",
            "տաս",
            "քսան",
            "երեսուն",
            "քառասքուն",
            "հիսուն",
            "վաթսուն",
            "յոթանասուն",
            "ութսուն",
            "իննսուն",
    };



    public static String casesBeforeThousand(long number){ //from 0 to 999
        if(number < 20){ //0-20
            return untillTwenty[(int)number];
        }
        else if(number < 100){ //from 20 to 99
            return untilHundred[(int) (number/10)] + untillTwenty[(int) (number%10)];
        }
        else if(number < 1000){ //from 100 to 999
            if(number/100 == 1){
                if(number%100 < 20){
                    return " հարյուր " + untillTwenty[(int)(number%100)];
                }
                return " հարյուր " + untilHundred[(int) ((number%100)/10)] + untillTwenty[(int) ((number%100)%10)];
            } else {
                if (number%100 < 20){
                    return untillTwenty[(int) (number/100)] + " հարյուր " + untillTwenty[(int)(number%100)];
                } else
                    return untillTwenty[(int) (number/100)] + " հարյուր " + untilHundred[(int) ((number%100)/10)] + untillTwenty[(int)((number%100)%10)];
            }
        } else return " ";
    }

    public static String casesBeforeMillion(long number){
        if(number < 10_000){ //from 1.000 to 9.999
            if(number/1000 == 1){
                return "հազար " + casesBeforeThousand(number%1000);
            } else
                return untillTwenty[(int)(number/1000)] + " հազար " + casesBeforeThousand(number%1000);
        }
        else if(number < 100_000){//from 10.000 to 99.999
            if(number/1000 < 20){ //first 2 digits
                return untillTwenty[(int)(number/1000)] + " հազար " + casesBeforeThousand(number%1000);
            }
            else
                return  untilHundred[(int)(number/10000)] + untillTwenty[(int) ((number/1000)%10)] + " հազար " + casesBeforeThousand(number%1000);
        }
        else if(number < 1_000_000){//from 100.000 - 999.999
            return casesBeforeThousand(number/1000) + " հազար " + casesBeforeThousand(number%1000);
        }
        else return " ";
    }

    public static String casesBeforeMilliard(long number){
        if(number < 10_000_000){ //1.000.000 to 9.999.999
            if(number%1_000_000 >= 1000){ //i.e. contains "thousand"
                   return untillTwenty[(int) (number/1_000_000)] + " միլիոն " + casesBeforeMillion(number%1_000_000);
            }
            else return untillTwenty[(int) (number/1_000_000)] + " միլիոն " + casesBeforeThousand(number%1_000_000);
        }
        else if(number < 100_000_000){ //10.000.000 to 99.999.999
            if(number/1_000_000 < 20){ //first 2 digits
                if(number%1_000_000 >= 1000){
                    return untillTwenty[(int) (number/1_000_000)] + " միլիոն " + casesBeforeMillion(number%1_000_000);
                }
                else return untillTwenty[(int) (number/1_000_000)] + " միլիոն " + casesBeforeThousand(number%1_000_000);
            }
            else {
                if(number%1_000_000 >= 1000){ //i.e. contains "thousand"
                    return untilHundred[(int) (number/10_000_000)] + untillTwenty[(int)((number/1_000_000)%10)] + " միլիոն " + casesBeforeMillion(number%1_000_000);
                }
                    else return untilHundred[(int) (number/10_000_000)] + untillTwenty[(int)((number/1_000_000)%10)] + " միլիոն " + casesBeforeThousand(number%1_000_000);
                }
            }
        else if(number < 1_000_000_000){ // from 100.000.000 to 999.999.999
            if(number%1_000_000 >= 1000){ //i.e. contains "thousand"
                return casesBeforeThousand(number/1_000_000) + " միլիոն " + casesBeforeMillion(number%1_000_000);
            }else return casesBeforeThousand(number/1_000_000) + " միլիոն " + casesBeforeThousand(number%1_000_000);
        }
        else return " ";
    }

    public static String casesBefore10Milliard(long number){
        if(number%1_000_000_000 >= 1_000_000){//i.e contains "million"
            return untillTwenty[(int) (number/1_000_000_000)] + " միլիարդ " + casesBeforeMilliard(number%1_000_000_000);
        }
        else if(number%1_000_000 >= 1000){//i.e. contains "thousand"
            return untillTwenty[(int) (number/1_000_000_000)] + " միլիարդ " + casesBeforeMillion(number%1_000_000);
        }
        else
            return untillTwenty[(int) (number/1_000_000_000)] + " միլիարդ " + casesBeforeThousand(number%1000);
    }



    public static String numToString(long number){
        if(number == 0)
            return "զրո";
        if(number < 0 || number >= 10_000_000_000L){
            return "invalid input";
        }

        if(number < 1000){ //0 to 999
            return casesBeforeThousand(number);
        }
        else if(number < 1_000_000){//1000 to 999.999
            return casesBeforeMillion(number);
        }
        else if(number < 1_000_000_000){ // 1.000.000 to 999.999.999
            return casesBeforeMilliard(number);
        }
        else if(number < 10_000_000_000l){ //1.000.000.000 to 9.999.999.999
            return casesBefore10Milliard(number);
        }
        else return " ";
    }
}