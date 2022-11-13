def dayOfTheWeek(self, day: int, month: int, year: int) -> str:
#    /*
#     * TASK:   given year, month, day, find which day in the week, i.e. Saturday. Year will be between
#               1971 and 2100
#     * 
#     * METHOD: calculate the contribution of year, month and day, and add offset （existence of leap year), finally
#               divide the contribution by 7 to get the result.
#     * 
#     * NOTE:   1. the week array in solution is rearranged such that the first day is in accordance with the first day
#                  in 1971
#               2. how to judge if it is a leap year? year % 400 == 0 or (year % 4 == 0 and year % 100 != 0)
#     * 
#     * TIME/
#     * SPACE:  O(N) / O(1) 
#     */
    week = ["Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"]
    month_ = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    acc = -1
    offset = 0
    acc += (year - 1971) * 365
    acc += sum(month_[0:(month - 1)])
    acc += day
    print(acc)
    i = 1971
    while (i <= year):
        if (i % 4 == 0 and i % 100 != 0) or (i % 400 == 0):
            offset += 1
        i += 1
    if not ((year % 4 == 0 and year % 100 != 0) or (year % 400 == 0)):
        acc += offset
    elif ((year % 4 == 0 and year % 100 != 0) or (year % 400 == 0)) and month >= 3:
        acc += offset
    else:
        acc += offset - 1
    print(acc)
    return week[acc % 7]