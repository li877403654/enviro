package envir.web.app.event.action;

import envir.web.app.event.support.UserEmitterMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zzl.
 * Date: 2018-07-14
 */
@Controller
public class ListenerAction {

    @RequestMapping("/event/listener")
    ResponseBodyEmitter execute(HttpServletRequest request) {
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        final SseEmitter emitter = new SseEmitter(0L);
        UserEmitterMap.add(userId, emitter);
        emitter.onCompletion(new Runnable() {
            @Override
            public void run() {
                UserEmitterMap.remove(userId, emitter);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(true) {
                    return;
                }
                for (int n = 1; n <= 5; n++) {
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        emitter.send("Hello " + n, MediaType.TEXT_PLAIN);
                    }
                    catch (IOException e) {
                        if(e.getClass().getSimpleName().equals("ClientAbortException")) {
                            UserEmitterMap.remove(userId, emitter);
                        }
                        e.printStackTrace();
                        break;
                    }
                }
                emitter.complete();
            }
        }).start();
        return emitter;
    }

}
