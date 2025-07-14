package org.cbr.oldFiles.generationsData;

import lombok.Getter;
import lombok.Setter;
import org.cbr.enums.Gender;
import org.cbr.enums.RussianRegion;
import org.cbr.enums.TaxRegion;

import java.time.LocalDate;
import java.util.Map;


@Getter
@Setter
public class GenerationConfig {
    private TaxRegion region;
    private  RussianRegion dataType;
    private LocalDate dateRangeStart;
    private LocalDate dateRangeEnd;
    private int minAge;
    private int maxAge;
    private Gender gender;
    private boolean realisticData;
    private Map<String, Object> customParameters;
}
