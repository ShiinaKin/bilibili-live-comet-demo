package ski.mashiro.util

import okio.ByteString
import org.apache.commons.lang3.StringUtils
import ski.mashiro.common.GlobalBean.JSON_MAPPER
import ski.mashiro.common.GlobalBean.config
import ski.mashiro.const.DataHeaderConsts.CLIENT_AUTHORIZE
import ski.mashiro.const.DataHeaderConsts.CLIENT_HEARTBEAT
import ski.mashiro.const.DataHeaderConsts.HEADER_LENGTH_INT
import ski.mashiro.const.DataHeaderConsts.HEARTBEAT_PROTOCOL
import ski.mashiro.entity.AuthorizeBody
import ski.mashiro.entity.DataHeader

/**
 * @author mashirot
 */
object HeartbeatUtils {

    fun generateAuthorizeBag(): ByteString {
        val authorizeBody =
            if (StringUtils.isNotBlank(config.cookie)) {
                AuthorizeBody(
                    config.uid,
                    config.roomId,
                    config.buvId,
                    config.key
                )
            } else {
                AuthorizeBody(config.roomId)
            }
        val body = JSON_MAPPER.writeValueAsBytes(authorizeBody)
        val totalLength = 16 + body.size
        val header = DataHeader(totalLength, HEARTBEAT_PROTOCOL, CLIENT_AUTHORIZE)
        return ReqUtils.getByteString(header, body)
    }

    fun generateHeartbeatBag(): ByteString {
        val header = DataHeader(HEADER_LENGTH_INT, HEARTBEAT_PROTOCOL, CLIENT_HEARTBEAT)
        return ReqUtils.getByteString(header, byteArrayOf())
    }

}