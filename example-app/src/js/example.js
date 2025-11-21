import { AlertButtons } from 'capacitor-alert-buttons';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    AlertButtons.showDialog({ buttons: [
        {
            text: "Ok",
            role: "default",
        },
        {
            text: "Cancel",
            role: "cancel",
        }
    ]})
}
