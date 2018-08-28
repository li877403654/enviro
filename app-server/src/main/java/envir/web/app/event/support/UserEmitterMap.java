package envir.web.app.event.support;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.*;

/**
 * Created by zzl.
 * Date: 2018-07-14
 */
public class UserEmitterMap {

    private static Map<Integer, List<SseEmitter>> _map = new HashMap<>();

    public static synchronized void add(Integer userId, SseEmitter emitter) {
        List<SseEmitter> emitters = _map.get(userId);
        if(emitters == null) {
            emitters = new ArrayList<>();
            _map.put(userId, emitters);
        }
        emitters.add(emitter);
    }

    public static synchronized List<SseEmitter> getEmitters(Integer userId) {
        List<SseEmitter> emitters = _map.get(userId);
        if(emitters == null) {
            return null;
        }
        return new ArrayList<>(emitters);
    }

    public static synchronized void remove(Integer userId, SseEmitter emitter) {
        List<SseEmitter> emitters = _map.get(userId);
        if(emitters != null) {
            emitters.remove(emitter);
        }
    }

}
