package ski.mashiro.handler

import ski.mashiro.common.GlobalBean.config
import ski.mashiro.const.CometConsts.INFO_CONTENT_IDX
import ski.mashiro.const.CometConsts.INFO_MEDAL_IDX
import ski.mashiro.const.CometConsts.INFO_STATUS_IDX
import ski.mashiro.const.CometConsts.INFO_USER_IDX
import ski.mashiro.const.CometConsts.MEDAL_ANCHORMAN_IDX
import ski.mashiro.const.CometConsts.MEDAL_ANCHORMAN_UID_IDX
import ski.mashiro.const.CometConsts.MEDAL_LEVEL_IDX
import ski.mashiro.const.CometConsts.MEDAL_NAME_IDX
import ski.mashiro.const.CometConsts.MEDAL_ROOM_ID_IDX
import ski.mashiro.const.CometConsts.TIMESTAMP_IDX
import ski.mashiro.const.CometConsts.USER_IS_ROOM_MANAGER_IDX
import ski.mashiro.const.CometConsts.USER_UID_IDX
import ski.mashiro.const.CometConsts.USER_USERNAME_IDX
import ski.mashiro.entity.Comet

/**
 * @author mashirot
 */
object CometHandler {

    fun handle(map: HashMap<*, *>) {
        val comet = trans2Comet(map["info"] as List<*>)
        println(comet)
    }

    private fun trans2Comet(info: List<*>): Comet {
        val content = info[INFO_CONTENT_IDX] as String

        val user = info[INFO_USER_IDX] as List<*>
        val uid = user[USER_UID_IDX].toString().toLong()
        val username = user[USER_USERNAME_IDX] as String
        val isRoomManager = user[USER_IS_ROOM_MANAGER_IDX] == 1
        val isAnchorman = uid == config.anchormanUID

        val status = info[INFO_STATUS_IDX] as List<*>
        val timestamp = status[TIMESTAMP_IDX] as Long

        val medal = info[INFO_MEDAL_IDX] as List<*>
        if (medal.isEmpty()) {
            return Comet(
                uid, username, content, isAnchorman, isRoomManager, timestamp
            )
        }

        val medalName = medal[MEDAL_NAME_IDX] as String
        val medalAnchorman = medal[MEDAL_ANCHORMAN_IDX] as String
        val medalAnchormanUID = medal[MEDAL_ANCHORMAN_UID_IDX].toString().toLong()
        val medalRoomId = medal[MEDAL_ROOM_ID_IDX].toString().toLong()
        val medalLevel = medal[MEDAL_LEVEL_IDX] as Int

        return Comet(
            uid,
            username,
            content,
            medalName,
            medalAnchorman,
            medalAnchormanUID,
            medalRoomId,
            medalLevel,
            isAnchorman,
            isRoomManager,
            timestamp
        )
    }

}