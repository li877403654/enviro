package envir.web.app.event.source;

import envir.sys.service.MsgService;
import envir.web.app.event.model.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzl.
 * Date: 2018-07-15
 */
@Component
public class MsgEventSource implements EventSource {

    private Integer lastMaxId;

    @Override
    public Integer getCheckInterval() {
        return 4000;
    }

    @Override
    public void init() {
        lastMaxId = msgService.getMaxId();
    }

    @Override
    public List<UserEvent> check() {
        Integer maxId = msgService.getMaxId();
        if(maxId == null || maxId.equals(lastMaxId)) {
            return null;
        }
        List<Integer> userIds = msgService.listUserIdsWithNewMsg(lastMaxId);

        List<UserEvent> userEvents = new ArrayList<>();
        for(Integer userId: userIds) {
            userEvents.add(new UserEvent(userId, "new-msg"));
        }

        //
        lastMaxId = maxId;
        return userEvents;
    }

    @Autowired
    private MsgService msgService;
}
