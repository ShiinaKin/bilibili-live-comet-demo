package ski.mashiro.common

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.WebSocket
import ski.mashiro.config.Config


/**
 * @author mashirot
 */
object GlobalBean {
    val JSON_MAPPER = JsonMapper().registerKotlinModule()
    val YAML_MAPPER = YAMLMapper().registerKotlinModule()
    val IO_SCOPE = CoroutineScope(Dispatchers.IO)
    lateinit var config: Config
    var webSocket: WebSocket? = null
}