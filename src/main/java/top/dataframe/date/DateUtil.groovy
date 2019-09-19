package top.dataframe.date


import groovy.transform.CompileStatic

@CompileStatic
class DateUtil implements Serializable {
    static String second2day(int second) {
        new Date((Long) second * 1000).format("yyyyMMdd")
    }

    static String yearFirstDay(String year) {
        Date.parse("yyyy", year).format("yyyyMMdd")
    }

    static String yearLastDay(String year) {
        String nextYear = ((year as int) + 1) + ""
        Calendar cal = Calendar.getInstance()
        cal.setTime(Date.parse("yyyy", nextYear))
        cal.set(Calendar.DAY_OF_MONTH, 0)
        cal.getTime().format("yyyyMMdd")
    }

    static String monthFirstDay(String month) {
        Date.parse("yyyyMM", month).format("yyyyMMdd")
    }

    static String monthLastDay(String month) {

        Calendar cal = Calendar.getInstance()
        cal.setTime(Date.parse("yyyyMM", month))
        cal.add(Calendar.MONTH, 1)
        cal.set(Calendar.DAY_OF_MONTH, 0)
        cal.getTime().format("yyyyMMdd")
    }

    static String quarterFirstDay(String quarter) {
        String[] arr = quarter.split("-")
        String year = arr[0]
        int q = arr[1] as int
        int month = (q - 1) * 3
        Calendar cal = Calendar.getInstance()
        cal.setTime(Date.parse("yyyy", year))
        cal.set(Calendar.MONTH, month)
        cal.getTime().format("yyyyMMdd")
    }

    static String quarterLastDay(String quarter) {
        String[] arr = quarter.split("-")
        String year = arr[0]
        int q = arr[1] as int
        int month = q * 3
        Calendar cal = Calendar.getInstance()
        cal.setTime(Date.parse("yyyy", year))
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.DAY_OF_MONTH, 0)
        cal.getTime().format("yyyyMMdd")
    }

    static List<String> parseStartAndEnd(String start, String end, String format, int defaultDays) {
        if (!(end?.trim())) {
            end = new Date().format(format) as int
        }
        if (!(start?.trim())) {
            start = (Date.parse(format, end as String) - defaultDays).format(format) as int
        }

        if (start.length() == 4) {
            start = DateUtil.yearFirstDay(start)
        } else if (start.length() == 6) {
            if (start.indexOf("-") <= 0) {
                start = DateUtil.monthFirstDay(start)
            } else {
                start = DateUtil.quarterFirstDay(start)
            }
        }

        if (end.length() == 4) {
            end = DateUtil.yearLastDay(end)
        } else if (end.length() == 6) {
            if (end.indexOf("-") <= 0) {
                end = DateUtil.monthLastDay(end)
            } else {
                end = DateUtil.quarterLastDay(end)
            }
        }

        [start, end]
    }
}
