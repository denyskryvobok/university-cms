package com.foxminded.university_cms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DateParser {

    public List<Integer> parseYearMonth(String yearMonth) {
        log.info("ParseYearMonth start with yearMoth:{}", yearMonth);
        Integer year = Integer.valueOf(yearMonth.substring(0, yearMonth.indexOf("-")));
        Integer month = Integer.parseInt(yearMonth.substring(yearMonth.indexOf("-") + 1));
        return List.of(month, year);
    }
}
