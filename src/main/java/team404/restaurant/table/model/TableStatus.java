package team404.restaurant.table.model;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;

public enum TableStatus {
    FREE, BUSY;

    public static final Collection<TableStatus> TABLE_STATUSES = Collections.unmodifiableCollection(
            Lists.newArrayList(FREE, BUSY)
    );
}
