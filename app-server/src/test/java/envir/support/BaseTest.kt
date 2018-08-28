package envir.support

import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import pub.spring.bean.BeanUtils

/**
 * Created by zzl.
 * Date: 2018-03-27
 */
@RunWith(SpringRunner::class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-*.xml")
open class BaseTest {

    @Before
    fun setup() {
        BeanUtils._ac = ac
    }

    @Autowired
    lateinit var ac: ApplicationContext

}