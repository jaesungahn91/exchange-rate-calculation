package io.spring.exchangeratecalculation.config.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({ValidationGroups.NotEmptyGroup.class, Default.class, ValidationGroups.PatternCheckGroup.class, })
public interface ValidationSequence {
}
