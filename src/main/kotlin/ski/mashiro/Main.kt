package ski.mashiro

import ski.mashiro.common.GlobalBean
import ski.mashiro.config.Config
import ski.mashiro.service.impl.WebSocketServiceImpl

/**
 * @author mashirot
 */
fun main() {
    GlobalBean.config = GlobalBean.YAML_MAPPER.readValue(
        """
            roomId: 850221
            uid: 0
            cookie: ""
            ua: "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
        """.trimIndent(),
        Config::class.java
    )
    WebSocketServiceImpl.connect2Room()
}

