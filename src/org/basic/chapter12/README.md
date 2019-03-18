새로운 날짜와 시간 API

    - 유효하지 않은 값이 입력되면, 런타임 시점에 오류를 낸다.
    - LocalDate: 시간을 제외한 날짜를 표현하는 불변 클래스
    - LocalTime: 시간을 표현하는 불변 클래스
    
    
기존 JAVA API 사용시 주이점

    - 월단위는 0부터 시작하므로 실수할 확율이 높다.
    - Calendar 클래스의 상수를 사용히는 편이 안전하다.
        ex)calendar.set(1999, Calendar.DECEMBER, 31)
        
Joda-TIme(https://www.joda.org/joda-time/)

    - LocalDate, DateTime 등으로 지역 시간과 시간대가 지정된 시간을 구분했다. LocalDate와 LocalTime으로 날짜와 시간을 별도의 클래스로 구분할 수도 있다.
    - plusDays, plusMinutes, plusSeconds 등 단위별 날짜 연산 메서드를 LocalDate, DateTime 클래스에서 지원한다. 메서드가 호출된 객체의 상태를 바꾸지 않고 새로운 객체를 반환한다. 불변 객체이다.
    - 월의 int 값과 명칭이 일치한다. 1월은 int 값 1이다.
    - GregorianChronology를 썼을 때는 1582년 10월을 특별하게 취급하지는 않는다. GJChronology를 사용하면 JDK의 GregorianCalendar와 같이 10월 4일 다음 날이 10월 15일로 나온다.
    - 서머타임 기간이면 DateTimeZone.isStandardOffset() 메서드의 반환값이 false이다.
    - 13월 같이 잘못 된 월이 넘어가면 객체 생성 시점에서 IllegalFieldValueException을 던진다.
    - 요일 상수는 일관되게 사용한다.
    - 잘못 된 시간대 ID 지정에는 IllegalArguementException을 던진다.


JSR-310: 새로운 Java의 날짜 API

    - DateTime 클래스대신 ZoneDateTime 클래스가 사용된다. 시간대 정보를 가지고 있는 클래스임을 더욱 명확히 표현하려 한 듯하다.
    - 요일 클래스는 Enum 상수로 제공한다. 잘못 지정하거나 혼동할 여지가 없다.
    - 생성자 대신 of() 메서드 같은 static factory 메서드를 많이 사용한다. DateTimeFormatter.ofPattern(), Instant.from() 등이 그 예이다. static factory 메서드는 가독성 있는 이름을 따로 붙일 수 있고, 생성자와는 달리 한번 생성된 객체를 재활용할 수도 있다.[30]
    - Joda-Time보다 클래스별 역할이 더 세분화되었다. ZoneRules 같은 클래스가 그 예이다.
    - 서머타임 기간이면 TimeZoneRules.isDaylightSavings() 메서드의 반환값이 true이다.
    - 잘못 지정돤 시간대 ID에는 ZoneRulesException을 던진다.
    - 잘못 된 월 지정에는 객체 생성 시점에서 DateTimeException을 던진다.
