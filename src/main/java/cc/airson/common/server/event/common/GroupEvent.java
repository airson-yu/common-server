package cc.airson.common.server.event.common;

import cc.airson.common.server.event.EventBase;

/**
 * 常用事件：群组相关操作
 *
 * @author airson http://airson.cc
 * @since 1.0 2020年6月9日17:22:19
 */
public class GroupEvent extends EventBase {

    private static final long serialVersionUID = 1L;

    public GroupEvent(Object source, int eventType, Long id) {
        super(source, eventType, id);
    }

}