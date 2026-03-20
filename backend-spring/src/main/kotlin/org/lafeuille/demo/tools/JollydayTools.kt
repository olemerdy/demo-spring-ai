package org.lafeuille.demo.tools

import de.focus_shift.jollyday.core.Holiday
import de.focus_shift.jollyday.core.HolidayManager
import de.focus_shift.jollyday.core.ManagerParameters
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import java.time.Year

class JollydayTools {
    @Tool(description = "Get the list of public holidays for provided year and calendar")
    fun getYearHolidays(
        @ToolParam(description = "Calendar code either in ISO 3166-1 alpha-2 format or ISO 3166-2 format") calendarPart: String,
        @ToolParam(description = "ISO Year") year: Year,
    ): List<Holiday> =
        HolidayManager
            .getInstance(ManagerParameters.create(calendarPart))
            .getHolidays(year)
            .stream()
            .sorted(Comparator.comparing { it.date })
            .toList()
}
