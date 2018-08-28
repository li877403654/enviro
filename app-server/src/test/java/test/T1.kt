package test

import org.apache.http.NameValuePair
import java.util.concurrent.TimeUnit
import org.apache.http.util.EntityUtils
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.message.BasicNameValuePair
import java.util.ArrayList
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.client.CloseableHttpClient



/**
 * Created by zzl.
 * Date: 2018-03-16
 */
object T1 {

    @JvmStatic
    fun main(args: Array<String>) {

        val httpclient = HttpClients.createDefault()

        val httpPost = HttpPost("https://sms.yunpian.com/v2/sms/single_send.json")
        val nvps = ArrayList<NameValuePair>()
        nvps.add(BasicNameValuePair("apikey", "8560fa348456c81b046d73f9b8a66010"))
        nvps.add(BasicNameValuePair("text", "【汉唐华盛】test1"))
        nvps.add(BasicNameValuePair("mobile", "13822181650"))
        httpPost.entity = UrlEncodedFormEntity(nvps)
        val response2 = httpclient.execute(httpPost)

        try {
            println(response2.statusLine)
            val entity2 = response2.entity
            // do something useful with the response body
            // and ensure it is fully consumed
//            EntityUtils.consume(entity2)
            val xx = EntityUtils.toString(entity2)
            println(xx)
        } finally {
            response2.close()
        }

    }
}