package caculate;

import java.time.LocalDate;
import java.time.Period;

/***
 @author sky
 @date 2019/12/29
 @version 1.0
 */
public class DataTime {
    private int Year;
    private int Month;
    private int Week=0;
    private int Day;
    private long Allday;
    public DataTime(){

    }
    public int getYear(){
        return Year;
    }
    public int getMonth(){
        return Month;
    }
    public int getWeek(){ return Week; }
    public int getDay(){
        return Day;
    }

    public long getAllday() {
        return Allday;
    }

    public void DataTime(LocalDate date, LocalDate date2){
        Week=0;
        if(date.isAfter(date2))
            Allday = date.toEpochDay() - date2.toEpochDay();
        else
            Allday = date2.toEpochDay() - date.toEpochDay();
        if(date.isAfter(date2)){
            Period period = Period.between(date2, date);
            Year = period.getYears();
            Month = period.getMonths();
            Day = period.getDays();
            while (Day>=7)
            if(Day>=7){
                Day=Day-7;
                Week++;
            }
        }
        else {
            Period period = Period.between(date, date2);
            Year = period.getYears();
            Month = period.getMonths();
            Day = period.getDays();
            while (Day>=7)
                if(Day>=7){
                    Day=Day-7;
                    Week++;
                }
        }
    }
}
