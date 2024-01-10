package ski.mashiro.util

import java.util.concurrent.ConcurrentHashMap

object LockUtils {

    private val lock = ConcurrentHashMap<String, Int>(1)
    private const val RECONNECT_LOCK = "reconnectLock"

    fun tryLock(): Boolean {
        if (lock.containsKey(RECONNECT_LOCK)) {
            return false
        }
        lock[RECONNECT_LOCK] = 1
        return true
    }

    fun releaseLock() = lock.remove(RECONNECT_LOCK)

}