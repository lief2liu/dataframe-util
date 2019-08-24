package top.dataframe.date


import groovy.transform.CompileStatic

@CompileStatic
class DateUtil implements Serializable {
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

    static String monthFirstDay(String year) {
        Date.parse("yyyyMM", year).format("yyyyMMdd")
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
}
