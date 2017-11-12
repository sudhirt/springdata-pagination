package com.sudhirt.samples.pagination.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractPaginationCriteria {

    private Integer pageNumber;
    private Integer pageSize;
}
