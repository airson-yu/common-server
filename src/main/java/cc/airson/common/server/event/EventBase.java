package cc.airson.common.server.event;

import io.swagger.annotations.Api;
import org.springframework.context.ApplicationEvent;

/**
 * 事件基类
 * <p>
 * https://juejin.im/post/5a8eb261f265da4e9e307230
 * https://juejin.im/post/5e076ed5518825126f374255
 *
 * @author airson http://airson.cc
 * @since 1.0 2020年6月9日17:22:19
 */
@Api(value = "事件基类", tags = "事件")
public class EventBase extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    /**
     * 操作类型：1-新增，2-修改，3-删除，4-修改关联关系
     */
    private int eventType;

    /**
     * 对象id
     */
    private Long id;

    public EventBase(Object source, int eventType, Long id) {
        super(source);
        this.eventType = eventType;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}