package org.basic.chapter12;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class Chapter12 {

    public static void main(String[] args) {
        // UTC: Universal Time Coordinate(협정 세계시)
        // GMT: Greenwich Mean Time(그리니치 표준시)
        // 시간대 목록 조회: https://www.iana.org/time-zones
//        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
//        zoneIds.stream().sorted().forEach(System.out::println);

//        LocalDate date = LocalDate.of(2019, 3, 15);
//        Temporal temporal = date.with(CustTemporalAdjusters.getNextWorkingDay());

//        getDateTimeFormatter();

        getZoneIds();

    }

    // LocalDateTime + ZoneId => ZoneDateTime
    public static void getZoneIds() {
        ZoneId zoneId = TimeZone.getDefault().toZoneId();

        ZoneId mexZoneId = ZoneId.of("America/Mexico_City");

        LocalDate date = LocalDate.of(2019, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(mexZoneId);

        LocalDateTime dateTime = LocalDateTime.of(2019, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(mexZoneId);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(mexZoneId);

        System.out.println(zdt1);
        System.out.println(zdt2);
        System.out.println(zdt3);
    }

    public static void getDateTimeFormatter() {
        LocalDate date1 = LocalDate.of(2019, 3,18);
        String f1 = date1.format(DateTimeFormatter.BASIC_ISO_DATE);
        String f2 = date1.format(DateTimeFormatter.ISO_LOCAL_DATE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fDate = date1.format(formatter);
        LocalDate date2 = LocalDate.parse(fDate, formatter);

        DateTimeFormatter italianFormatter =
                DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate date3 = LocalDate.of(2019, 3, 18);
        String iFormatDate = date3.format(italianFormatter);


        DateTimeFormatter coreanFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.YEAR)
                .appendLiteral("##")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral("####")
                .appendText(ChronoField.DAY_OF_MONTH)
                .parseCaseSensitive()
                .toFormatter(Locale.KOREAN);
        String cFormatDate = date3.format(coreanFormatter);
        System.out.println(cFormatDate);

    }

    public static void useTemporalAdjusters() {
        LocalDate date1 = LocalDate.of(2019, 3, 18);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(lastDayOfMonth());

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);

    }

    // between에 Instant와 LocalTime을 혼용할 수 없다.
    public static void getDurationOrPeriod() {
        LocalDateTime dt1 = LocalDateTime.of(2019, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(2019, Month.FEBRUARY, 01, 13, 45, 20);

        Duration d = Duration.between(dt1, dt2);
        long bDay1 = d.toDays();

        Period p = Period.between(
                LocalDate.of(2019, 3, 1),
                LocalDate.of(2019, 3, 18)
        );

        Period p1 = Period.ofDays(10);
        System.out.println(p1.getDays());
    }

    public static void getInstance() {

        Instant i1 = Instant.ofEpochSecond(3);
        Instant i2 = Instant.ofEpochSecond(3, 0);
        Instant i3 = Instant.ofEpochSecond(2, 1_000_000_000);
        Instant i4 = Instant.ofEpochSecond(4, -1_000_000_000);

        Instant now = Instant.now();
        System.out.println(now.toString());

        // 두 객체 사이의 시간의 간격을 보여주는 것이기 때문에, 시간관련 객체(LocalDateTime, LocalTime, Intstant)만 가능

    }


    public static void modifiedDate() {
        LocalDate date1 = LocalDate.of(2019, 3, 18);
        LocalDate date2 = date1.withYear(2018);
        LocalDate date3 = date2.withDayOfMonth(5);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 6);

        LocalDate date5 = date1.plusWeeks(1);
        LocalDate date6 = date5.minusYears(3);
        LocalDate date7 = date6.plus(6, ChronoUnit.MONTHS);

        System.out.println(date1);
        System.out.println(date5);
        System.out.println(date6);
        System.out.println(date7);

    }

    public void getLocalDateTime(LocalDate date, LocalTime time) {
        LocalDateTime dt1 = LocalDateTime.of(2019, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        System.out.println(dt1.toString());
        System.out.println(dt2.toString());
        System.out.println(dt3.toString());
        System.out.println(dt4.toString());
        System.out.println(dt5.toString());

    }

    public static LocalTime getLocalTime() {
        LocalTime time = LocalTime.of(13, 45, 20);

        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        return time;
    }

    public static LocalDate getLocalDate() {
        LocalDate date = LocalDate.of(2019, 3, 18);

        int year = date.getYear();
        int month = date.getMonth().getValue();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        year = date.get(ChronoField.YEAR);
        month = date.get(ChronoField.MONTH_OF_YEAR);
        day = date.get(ChronoField.DAY_OF_MONTH);

        return date;
    }
}
