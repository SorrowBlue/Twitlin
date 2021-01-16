/*
 * (c) 2021 SorrowBlue.
 */

package test

internal actual class Properties actual constructor(path: String) {

    private val text: String =
        nodeFS.readFileSync(nodePath.join(RESOURCE_ROOT, path), "utf-8") as String

    actual fun getProperty(key: String): String? {
        text.split("\n").forEach {
            val keyValue = it.split("=")
            if (keyValue.firstOrNull() == key) {
                return it.split("=").getOrNull(1)
            }
        }
        return null
    }

    actual fun getProperty(key: String, defaultValue: String): String {
        text.split("\n").forEach {
            val keyValue = it.split("=")
            if (keyValue.firstOrNull() == key) {
                return it.split("=").getOrNull(1) ?: defaultValue
            }
        }
        return defaultValue
    }
}
