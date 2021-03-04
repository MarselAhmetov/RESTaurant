package team404.restaurant.config.mapping;

import ma.glasnost.orika.MapperFactory;

public interface MapperFactoryConfigurer {

    void configure(MapperFactory mapperFactory);
}
