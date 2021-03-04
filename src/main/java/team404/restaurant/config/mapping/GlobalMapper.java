package team404.restaurant.config.mapping;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("globalMapper")
public class GlobalMapper extends ConfigurableMapper
        implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationContext applicationContext;

    public GlobalMapper() {
        super(false);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.init();
    }

    @Override
    protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) {
        super.configureFactoryBuilder(factoryBuilder);
        factoryBuilder.captureFieldContext(true);
        factoryBuilder.mappingContextFactory(new CustomMappingContext.Factory());
    }

    @Override
    protected void configure(MapperFactory mapperFactory) {
        super.configure(mapperFactory);

        Map<String, MapperFactoryConfigurer> mapOfConfigurers = applicationContext.getBeansOfType(MapperFactoryConfigurer.class);
        for (MapperFactoryConfigurer configurer : mapOfConfigurers.values()) {
            configurer.configure(mapperFactory);
        }
    }
}
