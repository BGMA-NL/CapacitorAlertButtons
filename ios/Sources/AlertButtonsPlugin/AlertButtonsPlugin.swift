import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(AlertButtonsPlugin)
public class AlertButtonsPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "AlertButtonsPlugin"
    public let jsName = "AlertButtons"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "showDialog", returnType: CAPPluginReturnPromise)
    ]

    @objc func showDialog(_ call: CAPPluginCall) {
        guard let buttons = call.getArray("buttons", JSObject.self) else {
            call.reject("No buttons provided")
            return
        }

        DispatchQueue.main.async {
            let style: UIAlertController.Style = UIDevice.current.userInterfaceIdiom == .pad ? .alert : .actionSheet
            let alert = UIAlertController(title: nil, message: nil, preferredStyle: style)

            if let popoverController = alert.popoverPresentationController {
                popoverController.sourceView = self.webView
                popoverController.sourceRect = CGRect(x: self.webView?.bounds.midX ?? 0, y: self.webView?.bounds.midY ?? 0, width: 0, height: 0)
                popoverController.permittedArrowDirections = []
            }

            for (index, button) in buttons.enumerated() {
                if let title = button.first(where: { $0.key == "text"})?.value as? String {
                    let style: UIAlertAction.Style = (button.first(where: { $0.key == "role"})?.value as? String == "cancel") ? .cancel : .default

                    let action = UIAlertAction(title: title, style: style) { _ in
                        call.resolve(["value": String(index)])
                    }

                    alert.addAction(action)
                }
            }

            if let rootVC = UIApplication.shared.windows.first?.rootViewController {
                rootVC.present(alert, animated: true, completion: nil)
            }
        }

    }
}
