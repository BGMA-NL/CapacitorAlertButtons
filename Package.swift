// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorAlertButtons",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "CapacitorAlertButtons",
            targets: ["AlertButtonsPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "AlertButtonsPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/AlertButtonsPlugin"),
        .testTarget(
            name: "AlertButtonsPluginTests",
            dependencies: ["AlertButtonsPlugin"],
            path: "ios/Tests/AlertButtonsPluginTests")
    ]
)
