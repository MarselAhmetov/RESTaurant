package team404.restaurant.position.model;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public enum PositionStatus {
    CREATED, COOKING, COOKED, DELIVERED;

    public static final List<PositionStatus> POSITION_STATUSES = Collections.unmodifiableList(
            Lists.newArrayList(
                    CREATED,
                    COOKING,
                    COOKED,
                    DELIVERED
            )
    );
}
