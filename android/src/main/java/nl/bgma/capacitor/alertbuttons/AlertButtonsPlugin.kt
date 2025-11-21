package nl.bgma.capacitor.alertbuttons

import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.onNegative
import com.example.awesomedialog.onPositive
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin
import org.json.JSONArray
import org.json.JSONException

@CapacitorPlugin(name = "AlertButtons")
class AlertButtonsPlugin : Plugin() {
    @PluginMethod
    fun showDialog(call: PluginCall) {
        val buttons: JSONArray? = call.getArray("buttons")

        if (buttons == null || buttons.length() == 0) {
            call.reject("No buttons provided")
            return
        }

        activity.runOnUiThread {
            val dialog = AwesomeDialog.build(activity)
            val buttonTitles = ArrayList<String>()

            try {
                for (i in 0 until buttons.length()) {
                    val btnObj = buttons.getJSONObject(i)
                    val text = btnObj.optString("text")
                    val role = btnObj.optString("role")

                    if ("cancel" == role) {
                        dialog.onNegative(text) {
                            call.resolve(JSObject().put("value", i.toString()))
                        }
                    } else {
                        dialog.onPositive(text) {
                            call.resolve(JSObject().put("value", i.toString()))
                        }
                    }

                    buttonTitles.add(text)
                }
            } catch (e: JSONException) {
                call.reject("Invalid button format")
                return@runOnUiThread
            }
        }
    }
}
