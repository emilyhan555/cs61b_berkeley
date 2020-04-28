public class LeapYear {
    /** Create a method that can tell if a Year is a leap year or not.  */
    public static boolean isLeapYear(final int year){
        if ((year%400 == 0) 
            || (year%4==0 && year%100 != 0) ){
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(final String[] args) {
        int year;
        int[] a = {2000,1999,2004,2100,2003};
        for (int i = 0; i< a.length; i++){
                year = a[i];
                isLeapYear(year);
        
        if (isLeapYear(year) == true) {
            System.out.println(year+ " is a leap year.");
        }
        else {
            System.out.println(year+" is not a leap year.");
        }
    }
}
}