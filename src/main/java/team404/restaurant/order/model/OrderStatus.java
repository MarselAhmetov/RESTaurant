package team404.restaurant.order.model;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public enum OrderStatus {
    CREATED, ACTIVE, CLOSED;

    public static final List<OrderStatus> ORDER_STATUSES = Collections.unmodifiableList(
            Lists.newArrayList(
                    CREATED,
                    ACTIVE,
                    CLOSED
            )
    );
}
