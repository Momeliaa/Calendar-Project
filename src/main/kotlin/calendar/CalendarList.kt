package calendar

import event.EventManager
import java.util.*

class CalendarList(private val year: Int, private val month: Int) {
    private val calendar: Calendar = Calendar.getInstance()

    public fun printCalendar(): Unit {
        calendar.set(year, month - 1, 1) // 월은 0부터 시작하므로 month - 1

        val lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        println("${this.year}년 ${this.month}월 달력")
        println("Sun\t\tMon\t\tTue\t\tWed\t\tThu\t\tFri\t\tSat")

        for(i in 1 until calendar.get(Calendar.DAY_OF_WEEK))
            print("\t\t")

        var currentDay = 1
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)

        while (currentDay <= lastDayOfMonth) {
            printDay()
            currentDay++
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                println()
            }
        }
    }

    private fun printDay(): Unit {
        val dayOfMonth = formatNumber(calendar.get(Calendar.DAY_OF_MONTH))
        print(dayOfMonth)

        val dayOfEvents=EventManager.searchEvents(Date("$year/${formatNumber(month)}/${dayOfMonth}","00:00:00"), false)
        if(dayOfEvents.isNotEmpty()) {
            print("(${dayOfEvents.count()})")
        }
        else
            print("\t")


        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            println("")
        } else {
            print("\t")
        }
    }

    private fun formatNumber(number: Int): String {
        return String.format("%02d", number)
    }
}

class Date {
    private val date:String
    private val time:String
    constructor(date:String, time:String) {
        this.date=date
        this.time=time

        //데이터 형식이 올바른지 비교 FIXME
    }

    public fun getData(): String {
        return date
    }

    public fun getTime(): String {
        return time
    }
}