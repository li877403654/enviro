package envir.sys.service;

import envir.sys.dao.MsgDao;
import envir.sys.entity.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pub.dao.GeneralDao;

import java.util.List;

/**
 * Created by zzl.
 * Date: 2018-06-20
 */
@Service
@Transactional(readOnly = true)
public class MsgService {

    public List<Msg> listByUserRem(Integer userId, boolean rem) {
        String sql = "select * from t_msg " +
                " where user_id = ? and rem_id is " + (rem? "not": "") + " null " +
                " order by id desc";
        return generalDao.queryList(Msg.class, sql, userId);
    }

    public Msg get(Integer id) {
        Msg msg = msgDao.get(id);
        Integer pushMsgId = msg.getPushMsgId();
        if(pushMsgId != null) {
            String sql = "select content from t_push_msg where id = ?";
            String detail = generalDao.queryValue(String.class, sql, pushMsgId);
            msg.setDetail(detail);
        }
        return msg;
    }

    @Transactional
    public void markRead(Integer id) {
        String sql = "update t_msg set `read` = 1 where id = ?";
        generalDao.execute(sql, id);
    }

    public List<String> listUnread(Integer userId) {
        String sql = "select content from t_msg " +
                " where user_id = ? and `read` = 0 order by id";
        return generalDao.queryList(String.class, sql, userId);
    }

    public String getLastMsg(Integer userId) {
        String sql = "select content from t_msg " +
                " where user_id = ? order by id desc limit 1";
        return generalDao.queryValue(String.class, sql, userId);
    }

    public Integer getMaxId() {
        return msgDao.getMaxId();
    }

    public List<Integer> listUserIdsWithNewMsg(int afterMsgId) {
        String sql = "select distinct user_id from t_msg where id > ?";
        return generalDao.queryList(Integer.class, sql, afterMsgId);
    }

    @Autowired
    private MsgDao msgDao;

    @Autowired
    private GeneralDao generalDao;

}
