package cc.airson.common.server.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 事件发布
 *
 * @author airson http://airson.cc
 * @since 1.0 2020年6月9日17:22:19
 */
@Component
public class EventPublisher {

    //private static Logger logger = ZLoggerFactory.getLogger(EventPublisher.class);

    @Autowired
    ApplicationContext applicationContext;

    public void publish(ApplicationEvent event) {

        applicationContext.publishEvent(event);

        //ContextLoader.getCurrentWebApplicationContext().publishEvent(event); // XXX 获取父容器发送事件

    }

}