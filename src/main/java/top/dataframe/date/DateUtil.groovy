package top.dataframe.date


import groovy.transform.CompileStatic

@CompileStatic
class DateUtil implements Serializable {
    static String yearFirstDay(String year) {
        Date.parse("yyyy", year).format("yyyyMMdd")
    }

    static String monthFirstDay(String year) {
        Date.parse("yyyyMM", year).format("yyyyMMdd")
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

    static String yearLastDay(String year) {
        Calendar cal = Calendar.getInstance()
        cal.setTime(Date.parse("yyyyMM", year+"12"))

    }

//    static yearhLastDay(String month, String fromFormat, String toFormat) {
//        Calendar cal = Calendar.getInstance()
//        cal.setTime(Date.parse(fromFormat, month))
//        cal.add(Calendar.MONTH,1)
//        cal.set(Calendar.DAY_OF_MONTH, 0)
//        cal.getTime().format(toFormat)
//    }

    public static void main(String[] args) {
        println yearLastDay("2019")
    }
}
